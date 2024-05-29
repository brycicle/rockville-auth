package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class ReservationHouseRequest {
    private String houseName;
    private BigDecimal housePrice;
    private BigDecimal multiplier;
}
