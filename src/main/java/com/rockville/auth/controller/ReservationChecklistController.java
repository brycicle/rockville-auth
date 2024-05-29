package com.rockville.auth.controller;

import com.rockville.auth.model.dto.*;
import com.rockville.auth.service.RequirementService;
import com.rockville.auth.service.ReservationChecklistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/reservation/checklist")
@RequiredArgsConstructor
public class ReservationChecklistController {

    private final ReservationChecklistService reservationChecklistService;
    @GetMapping
    @PostAuthorize("hasAnyAuthority('Admin', 'Sales_Agent')")
    public BaseResponse<List<ReservationChecklistResponse>> getReservationChecklist(@RequestParam(value = "type", required = false) String type) {
        log.info("ReservationChecklistController - getReservationChecklist");
        return new BaseResponse<>(
                reservationChecklistService.getReservationChecklist(type)
        );
    }
    @PostMapping
    public BaseResponse<ReservationChecklistResponse> createReservationChecklist(
            @RequestBody ReservationChecklistRequest request
    ) {
        log.info("ReservationChecklistController - createReservationChecklist");
        return new BaseResponse<>(
                reservationChecklistService.createReservationChecklist(request)
        );
    }
}
