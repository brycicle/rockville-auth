package com.rockville.auth.controller;

import com.rockville.auth.model.dto.*;
import com.rockville.auth.service.ReservationHistoryService;
import com.rockville.auth.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/v1/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationHistoryService reservationHistoryService;

    @PostMapping
    @PostAuthorize("hasAnyAuthority('Admin', 'Sales_Agent')")
    public BaseResponse<ReservationResponse> createReservation(@ModelAttribute ReservationRequest request) {
        log.info("ReservationController - createReservation {}", request);

        return new BaseResponse<>(
                reservationService.createReservation(request)
        );
    }

    @GetMapping
    @PostAuthorize("hasAnyAuthority('Admin', 'Sales_Agent')")
    public BaseResponse<Set<ReservationResponse>> getReservations(@AuthenticationPrincipal UserDetailsDto user) {
        log.info("ReservationController - getReservations {}", user);
        return new BaseResponse<>(
                reservationService.getReservations(user)
        );
    }
    @GetMapping("/documents/{reservationId}")
    @PostAuthorize("hasAnyAuthority('Admin', 'Sales_Agent')")
    public BaseResponse<Set<ReservationDocumentResponse>> getReservationDocuments(
            @AuthenticationPrincipal UserDetailsDto user, @PathVariable("reservationId") String reservationId
    ) {
        log.info("ReservationController - getReservationDocuments {} - {}", reservationId, user);
        return new BaseResponse<>(
                reservationService.getReservationDocuments(user, reservationId)
        );
    }
    @GetMapping("/history/{reservationId}")
    @PostAuthorize("hasAnyAuthority('Admin', 'Sales_Agent')")
    public BaseResponse<Set<ReservationHistoryResponse>> getReservationHistory(
            @AuthenticationPrincipal UserDetailsDto user, @PathVariable("reservationId") String reservationId
    ) {
        log.info("ReservationController - getReservationDocuments {} - {}", reservationId, user);
        return new BaseResponse<>(
                reservationHistoryService.getReservationHistoryByReservationId(reservationId)
        );
    }
}
