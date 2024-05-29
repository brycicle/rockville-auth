package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class LotHouseRequest {

    private String blockName;
    private String lotName;
    private String houseName;

}
