package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class HouseRequest {

    private String name;
    private String floor;
    private BigDecimal size;

    //private List<LotCoordinateRequest> coordinateRequests; //This is for Lot, Use as reference

}
