package com.rockville.auth.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HouseResponse {
    private String id;
    private String name;
    private BigDecimal lotArea;
    private BigDecimal floorArea;
    private BigDecimal price;
    private BigDecimal basePrice;
    private BigDecimal multiplier;
    private Set<HouseDetailResponse> details;
    private Set<HousePictureResponse> pictures;

    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;
}
