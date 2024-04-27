package com.rockville.auth.service;

import com.rockville.auth.model.domain.House;
import com.rockville.auth.model.dto.*;
import com.rockville.auth.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {
    private final HouseRepository repository;
    @Override
    public HouseResponse createHouse(HouseRequest request) {
        House house = repository.save(
                House.builder()
                        .name(request.getName())
                        .size(request.getSize())
                        .floors(request.getFloor())
                        .build()
        );
        return HouseResponse.builder()
                .id(house.getId())
                .name(house.getName())
                .size(house.getSize())
                .floors(house.getFloors())
                .build();
    }
}
