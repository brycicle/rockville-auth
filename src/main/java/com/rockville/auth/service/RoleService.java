package com.rockville.auth.service;

import com.rockville.auth.model.dto.RoleRequest;
import com.rockville.auth.model.dto.RoleResponse;

import java.util.List;

public interface RoleService {
    List<RoleResponse> getRoles();

    RoleResponse createRole(RoleRequest request);
}
