package com.rockville.auth.service;

import com.rockville.auth.model.domain.HouseDetail;
import com.rockville.auth.model.dto.HouseDetailRequest;
import com.rockville.auth.model.dto.HouseDetailResponse;
import com.rockville.auth.repository.HouseDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class HouseDetailServiceImpl implements HouseDetailService {

    private final HouseDetailRepository repository;

    @Override
    public List<HouseDetailResponse> getDetailsByHouseId(String houseId) {
        return repository.findAllByHouseIdEquals(houseId).stream().map(
                        houseDetail -> HouseDetailResponse.builder()
                                .houseId(houseDetail.getHouseId())
                                .detail(houseDetail.getDetails())
                                .createdBy(houseDetail.getCreatedBy())
                                .createdAt(houseDetail.getCreatedAt())
                                .updatedBy(houseDetail.getUpdatedBy())
                                .updatedAt(houseDetail.getUpdatedAt())
                                .build()
                )
                .sorted(Comparator.comparing(HouseDetailResponse::getCreatedAt))
                .collect(Collectors.toList());
    }

    @Override
    public HouseDetailResponse createHouseDetail(HouseDetailRequest request) {
        HouseDetail houseDetail = repository.save(
                HouseDetail.builder()
                        .houseId(request.getHouseId())
                        .details(request.getDetail())
                        .build()
        );
        return HouseDetailResponse.builder()
                .houseId(houseDetail.getHouseId())
                .detail(houseDetail.getDetails())
                .createdBy(houseDetail.getCreatedBy())
                .createdAt(houseDetail.getCreatedAt())
                .updatedBy(houseDetail.getUpdatedBy())
                .updatedAt(houseDetail.getUpdatedAt())
                .build();
    }
}
