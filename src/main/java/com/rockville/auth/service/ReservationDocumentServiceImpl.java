package com.rockville.auth.service;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.rockville.auth.config.AwsConfiguration;
import com.rockville.auth.model.domain.ReservationDocument;
import com.rockville.auth.model.dto.RequirementResponse;
import com.rockville.auth.model.dto.ReservationDocumentRequest;
import com.rockville.auth.model.dto.ReservationDocumentResponse;
import com.rockville.auth.repository.ReservationDocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationDocumentServiceImpl implements ReservationDocumentService {

    private final ReservationDocumentRepository repository;
    private final RequirementService requirementService;
    private final AwsConfiguration awsConfiguration;

    @Override
    public ReservationDocumentResponse addReservationDocument(ReservationDocumentRequest request) {

        String fileName = request.getRequirementCode() + Instant.now().toEpochMilli()
                + "." + FilenameUtils.getExtension(request.getFile().getOriginalFilename());

        ObjectMetadata data = new ObjectMetadata();
        data.setContentType(request.getFile().getContentType());
        data.setContentLength(request.getFile().getSize());

        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    "rockville-fs/reservation/" + request.getReservationId(),
                    fileName,
                    request.getFile().getInputStream(),
                    data
            );

            putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);

            // Upload the file into the S3 Bucket based on the request
            awsConfiguration.getAmazonClient()
                    .putObject(putObjectRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ReservationDocument reservationDocument = repository.save(
                ReservationDocument.builder()
                        .reservationId(request.getReservationId())
                        .fileType(FilenameUtils.getExtension(request.getFile().getOriginalFilename()))
                        .filePath("reservation/" + request.getReservationId() + "/" + fileName)
                        .requirementCode(request.getRequirementCode())
                        .build()
        );

        return ReservationDocumentResponse.builder()
                .id(reservationDocument.getId())
                .reservationId(reservationDocument.getReservationId())
                .filePath(reservationDocument.getFilePath())
                .fileType(reservationDocument.getFileType())
                .requirementCode(reservationDocument.getRequirementCode())
                .build();
    }

    @Override
    public Set<ReservationDocumentResponse> getReservationDocuments(String reservationId) {
        Set<ReservationDocumentResponse> documents = repository.findAllByReservationIdEquals(reservationId)
                .stream()
                .map(reservationDocument -> ReservationDocumentResponse.builder()
                        .id(reservationDocument.getId())
                        .reservationId(reservationDocument.getReservationId())
                        .filePath(reservationDocument.getFilePath())
                        .fileType(reservationDocument.getFileType())
                        .requirementCode(reservationDocument.getRequirementCode())
                        .createdBy(reservationDocument.getCreatedBy())
                        .createdAt(reservationDocument.getCreatedAt())
                        .updatedBy(reservationDocument.getUpdatedBy())
                        .updatedAt(reservationDocument.getUpdatedAt())
                        .build()
                ).collect(Collectors.toSet());


        Map<String, String> requirements = requirementService.getRequirements(
                        documents.stream()
                                .map(ReservationDocumentResponse::getRequirementCode)
                                .toList()
                )
                .stream()
                .collect(Collectors.toMap(RequirementResponse::getReservationCode, RequirementResponse::getName));

        return documents.stream().peek(document -> document.setRequirementName(requirements.get(document.getRequirementCode())))
                .collect(Collectors.toSet());
    }

    @Override
    public ReservationDocumentResponse getReservationDocument(String reservationDocumentId) {
        ReservationDocument reservationDocument = repository.findByIdEquals(reservationDocumentId);

        return ReservationDocumentResponse.builder()
                .id(reservationDocument.getId())
                .reservationId(reservationDocument.getReservationId())
                .filePath(reservationDocument.getFilePath())
                .fileType(reservationDocument.getFileType())
                .requirementCode(reservationDocument.getRequirementCode())
                .build();
    }
}
