package com.rockville.auth.service;

import com.rockville.auth.model.dto.*;

import java.util.Set;

public interface ReservationService {
    ReservationResponse createReservation(ReservationRequest request);
    Set<ReservationResponse> getReservations(UserDetailsDto user);
    Set<ReservationDocumentResponse> getReservationDocuments(UserDetailsDto user, String reservationId);
    ReservationDocumentResponse addReservationDocument(String reservationId, ReservationDocumentRequest request);
}
