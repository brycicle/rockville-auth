package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class LotRequest {

    private String blockName;
    private String lotName;
    private String status;
    private BigDecimal size;
    private List<LotCoordinateRequest> coordinates;

}
