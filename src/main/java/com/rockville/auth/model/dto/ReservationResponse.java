package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Builder
@Data
public class ReservationResponse {
    private String id;
    String lotId;
    String customerId;
    String contractPrice;

    private List<LotCoordinateResponse> coordinates;

    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;
}
