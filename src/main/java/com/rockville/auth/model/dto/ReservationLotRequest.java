package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class ReservationLotRequest {
    private String lotName;
    private String blockName;
    private BigDecimal lotPrice;
}
