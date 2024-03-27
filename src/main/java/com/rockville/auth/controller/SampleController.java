package com.rockville.auth.controller;

import com.rockville.auth.model.dto.SampleRequest;
import com.rockville.auth.model.dto.SampleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/sample")
@RequiredArgsConstructor
public class SampleController {

    @PostMapping
    public ResponseEntity<SampleResponse> test(@RequestBody SampleRequest request) {
        log.info("SampleController - test : {}", request);
        return ResponseEntity.ok(SampleResponse.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .timestamp(LocalDateTime.now())
                .build());
    }
}
