package com.rockville.auth.model.dto;

import brave.Tracer;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class BaseResponse<T> {
    private T data;
    private String traceId;
    private Instant timestamp;

    public BaseResponse(T data) {
        this.data = data;
        this.timestamp = Instant.now();
    }
}
