package com.rockville.auth.service;

import com.rockville.auth.model.dto.LotRequest;
import com.rockville.auth.model.dto.LotResponse;
import com.rockville.auth.model.dto.UserRequest;
import com.rockville.auth.model.dto.UserResponse;

import java.util.List;

public interface LotService {
    List<LotResponse> getLots();
//    TODO DELETE CREATE LOT
    LotResponse createLot(LotRequest request);
    List<LotResponse> createLots(List<LotRequest> requests);
}
