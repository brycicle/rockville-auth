package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class LotBlockRequest {
    private String blockName;
    private String lotName;
}
