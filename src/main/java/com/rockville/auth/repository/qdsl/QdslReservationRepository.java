package com.rockville.auth.repository.qdsl;

import com.rockville.auth.model.dto.ReservationResponse;
import com.rockville.auth.model.dto.UserDetailsDto;

import java.util.Set;

public interface QdslReservationRepository {
    Set<ReservationResponse> getReservationsByRole(UserDetailsDto user);
}
