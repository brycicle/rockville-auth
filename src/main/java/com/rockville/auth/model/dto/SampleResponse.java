package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SampleResponse {
    private String username;
    private String password;
    private LocalDateTime timestamp;
}
