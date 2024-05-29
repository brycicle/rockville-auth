package com.rockville.auth.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LotTypeResponse {
    private String id;
    private String lotType;
    private BigDecimal price;

    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;
}
