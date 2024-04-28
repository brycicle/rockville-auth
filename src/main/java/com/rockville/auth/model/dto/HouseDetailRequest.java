package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HouseDetailRequest {

    private String houseId;
    private String detail;

}
