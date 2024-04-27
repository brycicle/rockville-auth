package com.rockville.auth.repository.qdsl;

import com.rockville.auth.model.dto.LotResponse;

import java.util.List;

public interface QdslLotRepository {
    List<LotResponse> getLots();
}
