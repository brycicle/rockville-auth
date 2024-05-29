package com.rockville.auth.controller;

import com.rockville.auth.model.dto.BaseResponse;
import com.rockville.auth.model.dto.BulkLotHouseRequest;
import com.rockville.auth.model.dto.LotHouseRequest;
import com.rockville.auth.model.dto.LotHouseResponse;
import com.rockville.auth.service.LotHouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class LotHouseController {

    private final LotHouseService lotHouseService;

    @PostMapping("/lot:addHouse")
    public BaseResponse<LotHouseResponse> addHouse(@RequestBody LotHouseRequest request) {
        log.info("LotHouseController - addHouse - {}", request);
        return new BaseResponse<>(lotHouseService.addHouseToLot(request));
    }

    @PostMapping("/lot:addHouseBulk")
    public BaseResponse<List<LotHouseResponse>> addHouseToLots(@RequestBody BulkLotHouseRequest request) {
        log.info("LotHouseController - addHouseToLots - {}", request);
        return new BaseResponse<>(lotHouseService.addHouseToLots(request));
    }
}
