package com.rockville.auth.repository.qdsl;

import com.rockville.auth.model.domain.User;
import com.rockville.auth.model.dto.UserResponse;

import java.util.List;

public interface QdslUserRepository {
    List<User> getSalesAgents();
    List<UserResponse> getUsers();
}
