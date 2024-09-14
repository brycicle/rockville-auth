package com.rockville.auth.controller;

import com.rockville.auth.model.dto.*;
import com.rockville.auth.service.RoleService;
import com.rockville.auth.service.UserService;
import com.rockville.auth.util.JwtTokenUtil;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/account")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    @PostMapping
    public BaseResponse<UserResponse> createUser(@RequestBody UserRequest request) {
        log.info("UserController - createUser {}", request);
        return new BaseResponse<>(
                userService.createUser(request)
        );
    }
    @PostMapping("/bulk")
    public BaseResponse<List<UserResponse>> createUsers(@RequestBody List<UserRequest> request) {
        log.info("UserController - createUsers {}", request);
        return new BaseResponse<>(
                userService.createUsers(request)
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
    @GetMapping("/agents")
    @PostAuthorize("hasAnyAuthority('Admin', 'Accounts_Admin')")
    public BaseResponse<List<UserResponse>> getSalesAgents() {
        log.info("UserController - getSalesAgents");
        return new BaseResponse<>(userService.getSalesAgents());
    }
    @PutMapping
    public BaseResponse<UserDetailsDto> updateUser(@RequestBody UserRequest request) {
        UserDetailsDto user = (UserDetailsDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("UserController - updateUser {} - {}", user.getId(), request);
        userService.updateUser(user.getId(), request);

        user = (UserDetailsDto) userDetailsService.loadUserByUsername(user.getUsername());
        user.setToken(jwtTokenUtil.generateToken(user));

        return new BaseResponse<>(user);
    }
}
