package com.rockville.auth.service;

import com.rockville.auth.model.dto.ReservationHistoryRequest;
import com.rockville.auth.model.dto.ReservationHistoryResponse;

import java.util.List;
import java.util.Set;

public interface ReservationHistoryService {
    Set<ReservationHistoryResponse> getReservationHistoryByReservationId(String reservationId);
    ReservationHistoryResponse saveReservationHistory(ReservationHistoryRequest request);
    Set<ReservationHistoryResponse> saveReservationHistories(Set<ReservationHistoryRequest> requests);
}
