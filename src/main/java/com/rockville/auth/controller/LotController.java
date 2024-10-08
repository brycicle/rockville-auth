package com.rockville.auth.controller;

import com.rockville.auth.model.dto.BaseResponse;
import com.rockville.auth.model.dto.LotDashboardResponse;
import com.rockville.auth.model.dto.LotResponse;
import com.rockville.auth.service.LotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/api/lot")
@RequiredArgsConstructor
public class LotController {

    private final LotService lotService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<List<LotResponse>> getLots(@RequestParam(name = "status", required = false) String status) {
        log.info("LotController - getLots");
        return new BaseResponse<>(
                lotService.getLots(status)
        );
    }

    @GetMapping("/dashboard")
    public BaseResponse<LotDashboardResponse> getDashboardDetails() {
        log.info("LotController - getDashboardDetails");
        return new BaseResponse<>(lotService.getDashboardDetails());
    }

//    @PostMapping
//    public BaseResponse<LotResponse> createLot(@RequestBody LotRequest request) {
//        log.info("LotController - createLot {}", request);
//        return new BaseResponse<>(
//                lotService.createLot(request)
//        );
//    }
//    @PostMapping("/bulkSave")
//    public BaseResponse<List<LotResponse>> createLots(@RequestBody List<LotRequest> requests) {
//        log.info("LotController - createLots {}", requests);
//        return new BaseResponse<>(
//                lotService.createLots(requests)
//        );
//    }
}
