package com.rockville.auth.service;

import com.rockville.auth.model.domain.Lot;
import com.rockville.auth.model.dto.LotCoordinateResponse;
import com.rockville.auth.model.dto.LotRequest;
import com.rockville.auth.model.dto.LotResponse;
import com.rockville.auth.repository.LotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LotServiceImpl implements LotService {

    private final LotRepository repository;
    private final LotCoordinateService lotCoordinateService;

    @Override
    public List<LotResponse> getLots() {
        return repository.getLots();
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

        List<LotCoordinateResponse> lotCooridnates = new ArrayList<>();

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
    public List<LotResponse> createLots(List<LotRequest> requests) {
        List<LotResponse> response = new ArrayList<>();
        requests.forEach(lotRequest -> response.add(createLot(lotRequest)));
        return response;
    }
}
