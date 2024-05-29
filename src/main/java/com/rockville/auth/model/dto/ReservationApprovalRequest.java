package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReservationApprovalRequest {
    String reservationId;
    String type;
    Boolean isApproved;
    String roleApprover;
}
