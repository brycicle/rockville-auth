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
import java.util.List;

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

        List<HouseDetailResponse> details = new ArrayList<>();

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
}
