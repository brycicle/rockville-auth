package com.rockville.auth.service;

import com.rockville.auth.model.domain.Lot;
import com.rockville.auth.model.dto.LotCoordinateResponse;
import com.rockville.auth.model.dto.LotDashboardResponse;
import com.rockville.auth.model.dto.LotRequest;
import com.rockville.auth.model.dto.LotResponse;
import com.rockville.auth.repository.LotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class LotServiceImpl implements LotService {

    private final LotRepository repository;
    private final LotCoordinateService lotCoordinateService;
    @Override
    public List<LotResponse> getLots(String status) {
        return repository.getLots(status);
    }

    @Override
    public LotResponse createLot(LotRequest request) {
        Lot lot = repository.save(
                Lot.builder()
                        .blockName(request.getBlockName())
                        .lotName(request.getLotName())
                        .status(request.getStatus())
                        .size(request.getSize())
                        .build()
        );

        Set<LotCoordinateResponse> lotCooridnates = new HashSet<>();

        request.getCoordinates().forEach(
                lotCoordinateRequest -> {
                    lotCoordinateRequest.setLotId(lot.getId());
                    lotCooridnates.add(lotCoordinateService.createCoordinate(lotCoordinateRequest));
                }
        );

        return LotResponse.builder()
                .size(lot.getSize())
                .status(lot.getStatus())
                .blockName(lot.getBlockName())
                .lotName(lot.getLotName())
                .coordinates(lotCooridnates)
                .build();
    }

    @Override
    public LotResponse findLotByLotNameAndBlockName(String lotName, String blockName) {
        Lot lot = repository.findByLotNameEqualsAndBlockNameEquals(lotName, blockName)
                .orElseThrow();

        return LotResponse.builder()
                .id(lot.getId())
                .size(lot.getSize())
                .status(lot.getStatus())
                .type(lot.getType())
                .blockName(lot.getBlockName())
                .lotName(lot.getLotName())
                .build();
    }

    @Override
    public List<LotResponse> createLots(List<LotRequest> requests) {
        List<LotResponse> response = new ArrayList<>();
        requests.forEach(lotRequest -> response.add(createLot(lotRequest)));
        return response;
    }

    @Override
    public LotResponse findLotById(String lotId) {
        Lot lot = repository.findByIdEquals(lotId)
                .orElseThrow();

        return LotResponse.builder()
                .id(lot.getId())
                .size(lot.getSize())
                .status(lot.getStatus())
                .blockName(lot.getBlockName())
                .lotName(lot.getLotName())
                .build();
    }

    @Override
    public LotResponse updateLot(String lotId, LotRequest request) {
        Lot lot = repository.findByIdEquals(lotId).orElseThrow();
        lot.setType(request.getType());
        lot.setStatus(request.getStatus());

        lot = repository.save(lot);

        return LotResponse.builder()
                .id(lot.getId())
                .size(lot.getSize())
                .status(lot.getStatus())
                .blockName(lot.getBlockName())
                .lotName(lot.getLotName())
                .build();
    }

    @Override
    public LotDashboardResponse getDashboardDetails() {
        return LotDashboardResponse.builder()
                .totalAvailable(repository.countByStatusEquals("Available"))
                .build();
    }
}
