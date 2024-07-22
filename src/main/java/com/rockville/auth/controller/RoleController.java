package com.rockville.auth.controller;

import com.rockville.auth.model.dto.BaseResponse;
import com.rockville.auth.model.dto.RoleRequest;
import com.rockville.auth.model.dto.RoleResponse;
import com.rockville.auth.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/v1/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
    @GetMapping
    public BaseResponse<List<RoleResponse>> getRoles() {
        log.info("RoleController - getRoles");
        return new BaseResponse<>(
                roleService.getRoles()
        );
    }
    @PostMapping
    public BaseResponse<RoleResponse> createRole(@RequestBody RoleRequest request) {
        log.info("RoleController - createRole : {}", request);
        return new BaseResponse<>(
                roleService.createRole(request)
        );
    }
}
