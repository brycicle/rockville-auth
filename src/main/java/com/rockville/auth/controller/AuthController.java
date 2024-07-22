package com.rockville.auth.controller;

import com.rockville.auth.model.dto.*;
import com.rockville.auth.service.UserService;
import com.rockville.auth.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class AuthController {
    private final UserDetailsService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    @PostMapping("/login")
    public BaseResponse<UserDetails> login(@RequestBody LoginRequest request) {
        log.info("AuthController - login {}", request);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (final DisabledException disabledException) {
            throw new RuntimeException("USER_DISABLED", disabledException);
        } catch (final BadCredentialsException badCredentialsException) {
            throw new RuntimeException("Invalid Username/Password", badCredentialsException);
        }
        UserDetailsDto userDetailsDto = (UserDetailsDto) userService.loadUserByUsername(request.getUsername());
        userDetailsDto.setToken(jwtTokenUtil.generateToken(userDetailsDto));

        return new BaseResponse<>(userDetailsDto);
    }
}
