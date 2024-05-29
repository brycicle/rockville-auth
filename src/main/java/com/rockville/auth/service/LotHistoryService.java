package com.rockville.auth.service;

import com.rockville.auth.model.dto.LotHistoryRequest;
import com.rockville.auth.model.dto.LotHistoryResponse;
import com.rockville.auth.model.dto.ReservationHistoryRequest;
import com.rockville.auth.model.dto.ReservationHistoryResponse;

import java.util.List;
import java.util.Set;

public interface LotHistoryService {
    List<LotHistoryResponse> getLotHistoryByLotId(String lotId);
    LotHistoryResponse saveLotHistory(LotHistoryRequest request);
}
