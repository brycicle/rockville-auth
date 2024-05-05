package com.rockville.auth.service;

import com.rockville.auth.model.dto.ReservationChecklistRequest;
import com.rockville.auth.model.dto.ReservationChecklistResponse;

import java.util.List;

public interface ReservationChecklistService {
    ReservationChecklistResponse createReservationChecklist(ReservationChecklistRequest request);

    List<ReservationChecklistResponse> getReservationChecklist(String type);
}
