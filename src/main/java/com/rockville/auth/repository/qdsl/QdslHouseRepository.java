package com.rockville.auth.repository.qdsl;

import com.rockville.auth.model.dto.HouseResponse;

import java.util.List;

public interface QdslHouseRepository {
    List<HouseResponse> getHouses();
}
