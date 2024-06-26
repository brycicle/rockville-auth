package com.rockville.auth.service;

import com.rockville.auth.model.dto.HouseRequest;
import com.rockville.auth.model.dto.HouseResponse;

import java.util.List;

public interface HouseService {
    HouseResponse createHouse(HouseRequest request);
    List<HouseResponse> getHouses();
    HouseResponse findHouseByName(String name);
    HouseResponse findHouseById(String houseId);
}
