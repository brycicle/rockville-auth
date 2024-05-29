package com.rockville.auth.service;

import com.rockville.auth.model.dto.LotRequest;
import com.rockville.auth.model.dto.LotResponse;

import java.util.List;

public interface LotService {
    List<LotResponse> getLots();

    //    TODO DELETE CREATE LOT
    LotResponse createLot(LotRequest request);

    LotResponse findLotByLotNameAndBlockName(String lotName, String blockName);

    List<LotResponse> createLots(List<LotRequest> requests);

    LotResponse findLotById(String lotId);
    LotResponse updateLot(String lotId, LotRequest request);
}
