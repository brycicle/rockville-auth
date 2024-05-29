package com.rockville.auth.service;

import com.rockville.auth.model.dto.BulkLotHouseRequest;
import com.rockville.auth.model.dto.LotHouseRequest;
import com.rockville.auth.model.dto.LotHouseResponse;

import java.util.List;

public interface LotHouseService {
    LotHouseResponse addHouseToLot(LotHouseRequest request);
    List<LotHouseResponse> addHouseToLots(BulkLotHouseRequest request);
}
