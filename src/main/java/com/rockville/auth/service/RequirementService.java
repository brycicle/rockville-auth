package com.rockville.auth.service;

import com.rockville.auth.model.dto.HouseRequest;
import com.rockville.auth.model.dto.HouseResponse;
import com.rockville.auth.model.dto.RequirementRequest;
import com.rockville.auth.model.dto.RequirementResponse;

import java.util.List;

public interface RequirementService {
    RequirementResponse createRequirement(RequirementRequest request);
    List<RequirementResponse> getRequirements(List<String> reservationCodes);
    RequirementResponse getRequirement(String reservationCode);
}
