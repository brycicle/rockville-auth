package com.rockville.auth.service;

import com.rockville.auth.model.domain.User;
import com.rockville.auth.model.dto.UserDetailsDto;
import com.rockville.auth.repository.RoleRepository;
import com.rockville.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public final class AuthenticationManagerImpl implements AuthenticationManager {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional<User> user = userRepository.findByUsernameEquals(String.valueOf(authentication.getPrincipal()));
        if (user.isEmpty()) {
            throw new BadCredentialsException("User Does not exist : " + authentication.getPrincipal());
        }
        if (!passwordEncoder.matches(String.valueOf(authentication.getCredentials()), user.get().getPassword())) {
            throw new BadCredentialsException("Incorrect Password");
        }
        return new UsernamePasswordAuthenticationToken(user.get().getUsername(), null,
                roleRepository.findAllByUserIdEquals(user.get().getId()).stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                        .collect(Collectors.toList())
        );
    }
}
