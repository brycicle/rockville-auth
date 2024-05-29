package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class BulkLotHouseRequest {
    private List<String> lotIds;
    private List<LotBlockRequest> lotBlocks;
    private String houseName;
}
