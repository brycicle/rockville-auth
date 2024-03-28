package com.rockville.auth.controller;

import com.rockville.auth.model.dto.*;
import com.rockville.auth.service.LotService;
import com.rockville.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/lot")
@RequiredArgsConstructor
public class LotController {

    private final LotService lotService;
    @PostMapping
    public BaseResponse<LotResponse> createLot(@RequestBody LotRequest request) {
        log.info("LotController - createLot {}", request);
        return new BaseResponse<>(
                lotService.createLot(request)
        );
    }
}
