package com.rockville.auth.service;

import com.rockville.auth.model.domain.FileDocument;
import com.rockville.auth.model.dto.FileDocumentRequest;
import com.rockville.auth.model.dto.FileDocumentResponse;
import com.rockville.auth.repository.FileDocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileDocumentServiceImpl implements FileDocumentService {
    private final FileDocumentRepository repository;
    @Override
    public List<FileDocumentResponse> getDocuments() {
        return repository.findAllByIdIsNotNull()
                .stream()
                .map(fileDocument -> FileDocumentResponse.builder()
                        .id(fileDocument.getId())
                        .folder(fileDocument.getFolder())
                        .name(fileDocument.getName())
                        .filePath(fileDocument.getFilePath())
                        .createdBy(fileDocument.getCreatedBy())
                        .createdAt(fileDocument.getCreatedAt())
                        .updatedBy(fileDocument.getUpdatedBy())
                        .updatedAt(fileDocument.getUpdatedAt())
                        .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<FileDocumentResponse> saveDocuments(List<FileDocumentRequest> requests) {
        List<FileDocument> fileDocuments = new ArrayList<>();
        requests.forEach(
                request -> fileDocuments.add(
                        FileDocument.builder()
                                .name(request.getName())
                                .filePath(request.getFilePath())
                                .folder(request.getFolder())
                                .build()
                )
        );
        repository.saveAll(fileDocuments);
        return null;
    }
}
