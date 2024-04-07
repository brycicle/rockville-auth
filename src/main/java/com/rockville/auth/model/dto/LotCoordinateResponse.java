package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data
public class LotCoordinateResponse {
    private String id;
    private String lotId;
    private int coorX;
    private int coorY;

    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;
}
