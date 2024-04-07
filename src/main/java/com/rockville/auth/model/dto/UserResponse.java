package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data
public class UserResponse {
    private String id;
    private String username;
    private String password;
    private String email;
    private String contactNumber;
    private String firstName;
    private String middleName;
    private String lastName;

    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;
}
