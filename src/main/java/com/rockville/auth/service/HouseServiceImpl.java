package com.rockville.auth.service;

import com.rockville.auth.model.domain.House;
import com.rockville.auth.model.dto.*;
import com.rockville.auth.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseRepository repository;

    @Override
    public HouseResponse createHouse(HouseRequest request) {
        House house = repository.save(
                House.builder()
                        .type(request.getType())
                        .size(request.getSize())
                        .floors(request.getFloor())
                        .build()
        );
        return HouseResponse.builder()
                .id(house.getId())
                .type(house.getType())
                .size(house.getSize())
                .floors(house.getFloors())
                .build();
    }
}
