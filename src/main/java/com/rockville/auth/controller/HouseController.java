package com.rockville.auth.controller;

import com.rockville.auth.model.dto.*;
import com.rockville.auth.service.HouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}