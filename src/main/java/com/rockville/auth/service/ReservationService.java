package com.rockville.auth.service;

import com.rockville.auth.model.dto.ReservationDocumentResponse;
import com.rockville.auth.model.dto.ReservationRequest;
import com.rockville.auth.model.dto.ReservationResponse;
import com.rockville.auth.model.dto.UserDetailsDto;

import java.util.Set;

public interface ReservationService {
    ReservationResponse createReservation(ReservationRequest request);
    Set<ReservationResponse> getReservations(UserDetailsDto user);
    Set<ReservationDocumentResponse> getReservationDocuments(UserDetailsDto user, String reservationId);
}
