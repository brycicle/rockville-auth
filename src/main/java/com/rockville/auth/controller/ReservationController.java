package com.rockville.auth.controller;

import com.rockville.auth.model.dto.*;
import com.rockville.auth.service.CustomerService;
import com.rockville.auth.service.LotService;
import com.rockville.auth.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<List<ReservationResponse>> getReservation() {
        log.info("reservationController - getReservation");
        return new BaseResponse<>(
                reservationService.getReservation()
        );
    }
    @PostMapping
    public BaseResponse<ReservationResponse> createReservation(@RequestBody ReservationRequest request) {
        log.info("reservationController - createReservation {}", request);
        return new BaseResponse<>(
                reservationService.createReservation(request)
        );
    }
}
