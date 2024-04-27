package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Data
public class HouseResponse {
    private String id;
    private String name;
    private BigDecimal size;
    private String floors;


    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;
}
