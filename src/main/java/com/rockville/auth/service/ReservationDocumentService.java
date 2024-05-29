package com.rockville.auth.service;

import com.rockville.auth.model.dto.ReservationDocumentRequest;
import com.rockville.auth.model.dto.ReservationDocumentResponse;

import java.util.List;
import java.util.Set;

public interface ReservationDocumentService {
    ReservationDocumentResponse addReservationDocument(ReservationDocumentRequest request);
    Set<ReservationDocumentResponse> getReservationDocuments(String reservationId);
    ReservationDocumentResponse getReservationDocument(String reservationDocumentId);
}
