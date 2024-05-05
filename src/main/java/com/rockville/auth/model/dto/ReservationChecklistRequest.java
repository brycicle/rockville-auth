package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReservationChecklistRequest {
    private String type;
    private String requirementCode;
}
