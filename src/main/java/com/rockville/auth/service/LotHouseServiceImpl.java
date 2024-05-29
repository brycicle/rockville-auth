package com.rockville.auth.service;

import com.rockville.auth.model.domain.LotHouse;
import com.rockville.auth.model.dto.BulkLotHouseRequest;
import com.rockville.auth.model.dto.LotHouseRequest;
import com.rockville.auth.model.dto.LotHouseResponse;
import com.rockville.auth.model.dto.LotResponse;
import com.rockville.auth.repository.LotHouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LotHouseServiceImpl implements LotHouseService {

    private final LotHouseRepository repository;
    private final LotService lotService;

    @Override
    public LotHouseResponse addHouseToLot(LotHouseRequest request) {
        LotResponse lotResponse = lotService.findLotByLotNameAndBlockName(request.getLotName(), request.getBlockName());

        LotHouse lotHouse = repository.save(
                LotHouse.builder()
                        .lotId(lotResponse.getId())
                        .houseName(request.getHouseName())
                        .build()
        );
        return LotHouseResponse.builder()
                .id(lotHouse.getId())
                .lotId(lotHouse.getLotId())
                .houseName(lotHouse.getHouseName())
                .createdBy(lotHouse.getCreatedBy())
                .createdAt(lotHouse.getCreatedAt())
                .updatedBy(lotHouse.getUpdatedBy())
                .updatedAt(lotHouse.getUpdatedAt())
                .build();
    }

    @Override
    public List<LotHouseResponse> addHouseToLots(BulkLotHouseRequest request) {
        List<LotHouseResponse> lotHouseResponses = new ArrayList<>();
        if (!CollectionUtils.isEmpty(request.getLotIds())) {
            request.getLotIds().forEach(
                    lotId -> {
                        LotResponse lotResponse = lotService.findLotById(lotId);
                        lotHouseResponses.add(addHouseToLot(
                                LotHouseRequest.builder()
                                        .lotName(lotResponse.getLotName())
                                        .blockName(lotResponse.getBlockName())
                                        .houseName(request.getHouseName())
                                        .build()
                        ));
                    }
            );
        } else if (!CollectionUtils.isEmpty(request.getLotBlocks())) {
            request.getLotBlocks().forEach(
                    lotBlockRequest -> {
                        LotResponse lotResponse = lotService.findLotByLotNameAndBlockName(
                                lotBlockRequest.getLotName(), lotBlockRequest.getBlockName()
                        );
                        lotHouseResponses.add(addHouseToLot(
                                LotHouseRequest.builder()
                                        .lotName(lotResponse.getLotName())
                                        .blockName(lotResponse.getBlockName())
                                        .houseName(request.getHouseName())
                                        .build()
                        ));
                    }
            );
        }
        return lotHouseResponses;
    }
}
