package com.rockville.auth.service;

import com.rockville.auth.model.dto.CustomerRequest;
import com.rockville.auth.model.dto.CustomerResponse;
import com.rockville.auth.model.dto.ReservationRequest;
import com.rockville.auth.model.dto.ReservationResponse;

import java.util.List;

public interface ReservationService {
    List<ReservationResponse> getReservation();
    ReservationResponse createReservation(ReservationRequest request);
}
