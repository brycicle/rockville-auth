package com.rockville.auth.service;

import com.rockville.auth.model.domain.Lot;
import com.rockville.auth.model.domain.Role;
import com.rockville.auth.model.domain.User;
import com.rockville.auth.model.dto.*;
import com.rockville.auth.repository.LotRepository;
import com.rockville.auth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LotServiceImpl implements LotService {

    private final LotRepository repository;


    @Override
    public List<LotResponse> getLot() {
        return repository.findByLotNameEquals().stream()
                .map(lot -> LotResponse.builder()
                        .id(lot.getId())
                        .blockName(lot.getBlockName())
                        .lotName(lot.getLotName())
                        .status(lot.getStatus())
                        .size(lot.getSize())
                        .createdBy(lot.getCreatedBy())
                        .createdAt(lot.getCreatedAt())
                        .updatedBy(lot.getUpdatedBy())
                        .updatedAt(lot.getUpdatedAt())
                        .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public LotResponse createLot(LotRequest request) {
        Lot lot = repository.save(
                Lot.builder()
                        .blockName(request.getBlockName())
                        .lotName(request.getLotName())
                        .status(request.getStatus())
                        .size(request.getSize())
                        .build());
        return LotResponse.builder()
                .size(lot.getSize())
                .status(lot.getStatus())
                .blockName(lot.getBlockName())
                .lotName(lot.getLotName())
                .build();
    }
}
