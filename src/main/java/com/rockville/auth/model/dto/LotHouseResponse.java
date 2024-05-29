package com.rockville.auth.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LotHouseResponse {
    private String id;
    private String lotId;
    private String houseName;

    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;
}
