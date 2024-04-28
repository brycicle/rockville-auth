package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class HouseRequest {

    private String name;
    private BigDecimal lotArea;
    private BigDecimal floorArea;
    private BigDecimal price;
    private BigDecimal multiplier;
    private List<HouseDetailRequest> details;

}
