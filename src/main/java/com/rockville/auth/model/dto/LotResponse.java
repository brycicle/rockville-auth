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
public class LotResponse {
    private String id;
    private String blockName;
    private String lotName;
    private String blockLotName;
    private String status;
    private String type;
    private BigDecimal size;
    private Instant lotAvailability;

    private Set<LotCoordinateResponse> coordinates;
    private Set<LotHouseResponse> lotHouses;
    private LotTypeResponse lotType;

    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;
}
