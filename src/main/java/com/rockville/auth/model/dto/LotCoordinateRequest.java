package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LotCoordinateRequest {

    private String lotId;
    private int coorX;
    private int coorY;

}
