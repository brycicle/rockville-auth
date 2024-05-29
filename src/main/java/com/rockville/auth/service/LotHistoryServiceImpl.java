package com.rockville.auth.service;

import com.rockville.auth.model.domain.LotHistory;
import com.rockville.auth.model.dto.LotHistoryRequest;
import com.rockville.auth.model.dto.LotHistoryResponse;
import com.rockville.auth.repository.LotHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LotHistoryServiceImpl implements LotHistoryService {

    private final LotHistoryRepository repository;

    @Override
    public List<LotHistoryResponse> getLotHistoryByLotId(String lotId) {
        return repository.findAllByLotIdEquals(lotId).stream()
                .map(lotHistory -> LotHistoryResponse.builder()
                        .id(lotHistory.getId())
                        .lotId(lotHistory.getLotId())
                        .action(lotHistory.getAction())
                        .type(lotHistory.getType())
                        .description(lotHistory.getDescription())
                        .customerId(lotHistory.getCustomerId())
                        .userId(lotHistory.getUserId())
                        .createdBy(lotHistory.getCreatedBy())
                        .createdAt(lotHistory.getCreatedAt())
                        .updatedBy(lotHistory.getUpdatedBy())
                        .updatedAt(lotHistory.getUpdatedAt())
                        .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public LotHistoryResponse saveLotHistory(LotHistoryRequest request) {
        LotHistory lotHistory = repository.save(
                LotHistory.builder()
                        .lotId(request.getReservationId())
                        .action(request.getAction())
                        .type(request.getType())
                        .description(request.getDescription())
                        .customerId(request.getCustomerId())
                        .userId(request.getUserId())
                        .build()
        );

        return LotHistoryResponse.builder()
                .id(lotHistory.getId())
                .lotId(lotHistory.getLotId())
                .action(lotHistory.getAction())
                .type(lotHistory.getType())
                .description(lotHistory.getDescription())
                .customerId(lotHistory.getCustomerId())
                .userId(lotHistory.getUserId())
                .createdBy(lotHistory.getCreatedBy())
                .createdAt(lotHistory.getCreatedAt())
                .updatedBy(lotHistory.getUpdatedBy())
                .updatedAt(lotHistory.getUpdatedAt())
                .build();
    }
}
