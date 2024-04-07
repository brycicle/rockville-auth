package com.rockville.auth.service;

import com.rockville.auth.model.domain.LotCoordinate;
import com.rockville.auth.model.dto.LotCoordinateRequest;
import com.rockville.auth.model.dto.LotCoordinateResponse;
import com.rockville.auth.repository.LotCoordinateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LotCoordinateServiceImpl implements LotCoordinateService {

    private final LotCoordinateRepository repository;

    @Override
    public List<LotCoordinateResponse> getCoordinateByLotId(String lotId) {
        return repository.findAllByLotIdEquals(lotId).stream().map(
                lotCoordinate -> LotCoordinateResponse.builder()
                        .lotId(lotCoordinate.getLotId())
                        .coorX(lotCoordinate.getCoorX())
                        .coorY(lotCoordinate.getCoorY())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public List<LotCoordinateResponse> createCoordinate(List<LotCoordinateRequest> requests) {
//        Create Emply LotCoordinateResponse List
        List<LotCoordinateResponse> lotCoordinates = new ArrayList<>();

//        Loop all reqests
        requests.forEach(
                lotCoordinateRequest -> {
//                    For all request inside requests, save lotCoordinate
                    LotCoordinate lotCoordinate = repository.save(
                            LotCoordinate.builder()
                                    .lotId(lotCoordinateRequest.getLotId())
                                    .coorX(lotCoordinateRequest.getCoorX())
                                    .coorY(lotCoordinateRequest.getCoorY())
                                    .build()
                    );
//                    For All saved lot coordinate, add to empty list
                    lotCoordinates.add(
                            LotCoordinateResponse.builder()
                                    .lotId(lotCoordinate.getLotId())
                                    .coorX(lotCoordinate.getCoorX())
                                    .coorY(lotCoordinate.getCoorY())
                                    .build()
                    );
                }
        );

        return lotCoordinates;
    }

}
