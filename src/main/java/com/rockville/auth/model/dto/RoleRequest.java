package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data
public class RoleRequest {
    private String id;
    private String userId;
    private String name;
}
