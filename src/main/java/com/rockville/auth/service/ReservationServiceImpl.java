package com.rockville.auth.service;

import com.rockville.auth.model.domain.Lot;
import com.rockville.auth.model.domain.Reservation;
import com.rockville.auth.model.dto.*;
import com.rockville.auth.repository.LotRepository;
import com.rockville.auth.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository repository;
    private final CustomerService customerService;
    private final LotService lotService;


    @Override
    public List<ReservationResponse> getReservation() {
        return repository.findAllByIdIsNotNull()
                .stream()
                .map(reservation -> ReservationResponse.builder()
                        .id(reservation.getId())
                        .lotId(reservation.getLotId())
                        .customerId(reservation.getCustomerId())
                        .contractPrice(reservation.getContractPrice())
                        .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public ReservationResponse createReservation(ReservationRequest request) {
        Reservation reservation = repository.save(
                Reservation.builder()
                        .lotId(request.getLotId())
                        .customerId(request.getCustomerId())
                        .contractPrice(request.getContractPrice())
                        .build()
        );
        return ReservationResponse.builder()
                .id(reservation.getId())
                .lotId(reservation.getLotId())
                .customerId(reservation.getCustomerId())
                .contractPrice(reservation.getContractPrice())
                .build();
    }
}
