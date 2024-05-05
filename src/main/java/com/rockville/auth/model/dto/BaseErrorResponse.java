package com.rockville.auth.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class BaseErrorResponse extends BaseResponse {
    private String error;
    private Instant timestamp;

    public BaseErrorResponse(String error) {
        this.error = error;
        this.timestamp = Instant.now();
    }
}
