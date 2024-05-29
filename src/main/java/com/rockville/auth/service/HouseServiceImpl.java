package com.rockville.auth.service;

import com.rockville.auth.model.domain.House;
import com.rockville.auth.model.dto.*;
import com.rockville.auth.repository.HouseDetailRepository;
import com.rockville.auth.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {
    private final HouseRepository repository;
    private final HouseDetailService houseDetailService;

    @Override
    public HouseResponse createHouse(HouseRequest request) {
        House house = repository.save(
                House.builder()
                        .name(request.getName())
                        .lotArea(request.getLotArea())
                        .floorArea(request.getFloorArea())
                        .price(request.getPrice())
                        .multiplier(request.getMultiplier())
                        .build()
        );

        Set<HouseDetailResponse> details = new HashSet<>();

        if (!CollectionUtils.isEmpty(request.getDetails())) {
            request.getDetails().forEach(
                    houseDetailRequest -> {
                        houseDetailRequest.setHouseId(house.getId());
                        details.add(houseDetailService.createHouseDetail(houseDetailRequest));
                    }
            );
        }

        return HouseResponse.builder()
                .id(house.getId())
                .name(house.getName())
                .lotArea(house.getLotArea())
                .floorArea(house.getFloorArea())
                .multiplier(house.getMultiplier())
                .price(house.getPrice())
                .details(details)
                .createdBy(house.getCreatedBy())
                .createdAt(house.getCreatedAt())
                .updatedBy(house.getUpdatedBy())
                .updatedAt(house.getUpdatedAt())
                .build();
    }

    @Override
    public List<HouseResponse> getHouses() {
        return repository.getHouses();
    }

    @Override
    public HouseResponse findHouseByName(String name) {
        House house = repository.findByNameEquals(name).orElseThrow(RuntimeException::new);

        return HouseResponse.builder()
                .id(house.getId())
                .name(house.getName())
                .lotArea(house.getLotArea())
                .floorArea(house.getFloorArea())
                .multiplier(house.getMultiplier())
                .price(house.getPrice())
                .createdBy(house.getCreatedBy())
                .createdAt(house.getCreatedAt())
                .updatedBy(house.getUpdatedBy())
                .updatedAt(house.getUpdatedAt())
                .build();
    }

    @Override
    public HouseResponse findHouseById(String houseId) {
        House house = repository.findByIdEquals(houseId).orElseThrow(RuntimeException::new);

        return HouseResponse.builder()
                .id(house.getId())
                .name(house.getName())
                .lotArea(house.getLotArea())
                .floorArea(house.getFloorArea())
                .multiplier(house.getMultiplier())
                .price(house.getPrice())
                .createdBy(house.getCreatedBy())
                .createdAt(house.getCreatedAt())
                .updatedBy(house.getUpdatedBy())
                .updatedAt(house.getUpdatedAt())
                .build();
    }
}
