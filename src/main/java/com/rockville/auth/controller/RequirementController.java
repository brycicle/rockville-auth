package com.rockville.auth.controller;

import com.rockville.auth.model.dto.*;
import com.rockville.auth.service.CustomerService;
import com.rockville.auth.service.RequirementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/requirement")
@RequiredArgsConstructor
public class RequirementController {

    private final RequirementService requirementService;
    @GetMapping
    public BaseResponse<List<RequirementResponse>> getRequirements(
            @RequestParam("reservationCodes") List<String> reservationCodes
    ) {
        log.info("RequirementController - getRequirements");
        return new BaseResponse<>(
                requirementService.getRequirements(reservationCodes)
        );
    }
    @PostMapping
    public BaseResponse<RequirementResponse> createRequirement(@RequestBody RequirementRequest request) {
        log.info("RequirementController - createRequirement");
        return new BaseResponse<>(
                requirementService.createRequirement(request)
        );
    }
}
