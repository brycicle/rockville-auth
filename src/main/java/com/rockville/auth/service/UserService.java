package com.rockville.auth.service;

import com.rockville.auth.model.dto.RoleRequest;
import com.rockville.auth.model.dto.RoleResponse;
import com.rockville.auth.model.dto.UserRequest;
import com.rockville.auth.model.dto.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getUsers();
    List<UserResponse> getSalesAgents();
    UserResponse createUser(UserRequest request);
    UserResponse updateUser(String userId, UserRequest request);
}
