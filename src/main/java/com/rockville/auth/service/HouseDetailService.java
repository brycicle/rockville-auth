package com.rockville.auth.service;

import com.rockville.auth.model.dto.HouseDetailRequest;
import com.rockville.auth.model.dto.HouseDetailResponse;
import com.rockville.auth.model.dto.LotCoordinateRequest;
import com.rockville.auth.model.dto.LotCoordinateResponse;

import java.util.List;

public interface HouseDetailService {
    List<HouseDetailResponse> getDetailsByHouseId(String houseId);

    HouseDetailResponse createHouseDetail(HouseDetailRequest requests);
}
