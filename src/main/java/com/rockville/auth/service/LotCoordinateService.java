package com.rockville.auth.service;

import com.rockville.auth.model.dto.LotCoordinateRequest;
import com.rockville.auth.model.dto.LotCoordinateResponse;

import java.util.List;

public interface LotCoordinateService {
    List<LotCoordinateResponse> getCoordinateByLotId(String lotId);

    List<LotCoordinateResponse> createCoordinate(List<LotCoordinateRequest> requests);
}
