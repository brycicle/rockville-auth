package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data
public class ReservationApprovalResponse {
    String id;
    String reservationId;
    String type;
    Boolean isApproved;
    String roleApprover;
    String createdBy;
    Instant createdAt;
    String updatedBy;
    Instant updatedAt;
}
