package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginRequest {

    private String username;
    private String password;

}
