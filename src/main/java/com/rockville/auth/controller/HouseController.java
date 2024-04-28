package com.rockville.auth.controller;

import com.rockville.auth.model.dto.*;
import com.rockville.auth.service.HouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/house")
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;
    @PostMapping
    public BaseResponse<HouseResponse> createHouse(@RequestBody HouseRequest request) {
        log.info("HouseController - createHouse {}", request);
        return new BaseResponse<>(
                houseService.createHouse(request)
        );
    }
    @GetMapping
    public BaseResponse<List<HouseResponse>> getHouses() {
        log.info("HouseController - getHouses");
        return new BaseResponse<>(houseService.getHouses());
    }
}