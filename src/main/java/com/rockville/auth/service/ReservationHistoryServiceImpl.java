package com.rockville.auth.service;

import com.rockville.auth.model.domain.ReservationHistory;
import com.rockville.auth.model.dto.ReservationHistoryRequest;
import com.rockville.auth.model.dto.ReservationHistoryResponse;
import com.rockville.auth.repository.ReservationHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationHistoryServiceImpl implements ReservationHistoryService {

    private final ReservationHistoryRepository repository;
    private final LotService lotService;
    private final CustomerService customerService;
    private final HouseService houseService;
    private final ReservationDocumentService reservationDocumentService;
    @Override
    public Set<ReservationHistoryResponse> getReservationHistoryByReservationId(String reservationId) {
        return repository.findAllByReservationIdEquals(reservationId).stream()
                .map(reservationHistory -> ReservationHistoryResponse.builder()
                        .reservationId(reservationHistory.getReservationId())
                        .action(reservationHistory.getAction())
                        .type(reservationHistory.getType())
                        .description(reservationHistory.getDescription())
                        .house(Objects.nonNull(reservationHistory.getHouseId()) ?
                                houseService.findHouseById(reservationHistory.getHouseId())
                                : null
                        )
                        .lot(Objects.nonNull(reservationHistory.getLotId()) ?
                                lotService.findLotById(reservationHistory.getLotId())
                                : null
                        )
                        .document(Objects.nonNull(reservationHistory.getReservationDocumentId()) ?
                                reservationDocumentService.getReservationDocument(
                                    reservationHistory.getReservationDocumentId()
                                ) : null
                        )
                        .createdBy(reservationHistory.getCreatedBy())
                        .createdAt(reservationHistory.getCreatedAt())
                        .updatedBy(reservationHistory.getUpdatedBy())
                        .updatedAt(reservationHistory.getUpdatedAt())
                        .build()
                )
                .sorted(Comparator.comparing(ReservationHistoryResponse::getCreatedAt).reversed())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public ReservationHistoryResponse saveReservationHistory(ReservationHistoryRequest request) {
        ReservationHistory reservationHistory = repository.save(
                ReservationHistory.builder()
                        .reservationId(request.getReservationId())
                        .action(request.getAction())
                        .type(request.getType())
                        .description(request.getDescription())
                        .houseId(request.getHouseId())
                        .lotId(request.getLotId())
                        .reservationDocumentId(request.getReservationDocumentId())
                        .reservationApprovalId(request.getReservationApprovalId())
                        .build()
        );

        return ReservationHistoryResponse.builder()
                .reservationId(reservationHistory.getReservationId())
                .action(reservationHistory.getAction())
                .type(reservationHistory.getType())
                .description(reservationHistory.getDescription())
                .houseId(reservationHistory.getHouseId())
                .lotId(reservationHistory.getLotId())
                .reservationDocumentId(reservationHistory.getReservationDocumentId())
                .reservationApprovalId(reservationHistory.getReservationApprovalId())
                .createdBy(reservationHistory.getCreatedBy())
                .createdAt(reservationHistory.getCreatedAt())
                .updatedBy(reservationHistory.getUpdatedBy())
                .updatedAt(reservationHistory.getUpdatedAt())
                .build();
    }

    @Override
    public Set<ReservationHistoryResponse> saveReservationHistories(Set<ReservationHistoryRequest> requests) {
        Set<ReservationHistoryResponse> reservationHistoryResponses = requests.stream()
                .map(this::saveReservationHistory)
                .collect(Collectors.toSet());
        return null;
    }
}
