package com.rockville.auth.controller;

import com.rockville.auth.model.dto.*;
import com.rockville.auth.service.RoleService;
import com.rockville.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/account")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping
    public BaseResponse<UserResponse> createUser(@RequestBody UserRequest request) {
        log.info("UserController - createUser {}", request);
        return new BaseResponse<>(
                userService.createUser(request)
        );
    }
    @PutMapping("/{userId}")
    public BaseResponse<UserResponse> updateUser(
            @PathVariable("userId") String userId,
            @RequestBody UserRequest request
    ) {
        log.info("UserController - updateUser {} - {}", userId, request);
        return new BaseResponse<>(
                userService.updateUser(userId, request)
        );
    }
}
