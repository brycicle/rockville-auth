package com.rockville.auth.service;

import com.rockville.auth.model.dto.LotTypeResponse;

public interface LotTypeService {
    LotTypeResponse findLotTypeByType(String lotType);
}
