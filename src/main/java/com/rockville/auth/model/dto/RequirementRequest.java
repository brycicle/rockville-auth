package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RequirementRequest {
    private String reservationCode;
    private String name;
}
