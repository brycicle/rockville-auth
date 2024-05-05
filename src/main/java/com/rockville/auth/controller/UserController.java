package com.rockville.auth.controller;

import com.rockville.auth.model.dto.*;
import com.rockville.auth.service.RoleService;
import com.rockville.auth.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @GetMapping
    @PostAuthorize("hasAnyAuthority('Admin')")
    public BaseResponse<List<UserResponse>> getUsers() {
        log.info("UserController - getUsers");
        return new BaseResponse<>(
                userService.getUsers()
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
