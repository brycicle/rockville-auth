package com.rockville.auth.service;

import com.rockville.auth.model.domain.Role;
import com.rockville.auth.model.domain.User;
import com.rockville.auth.model.dto.RoleRequest;
import com.rockville.auth.model.dto.RoleResponse;
import com.rockville.auth.model.dto.UserRequest;
import com.rockville.auth.model.dto.UserResponse;
import com.rockville.auth.repository.RoleRepository;
import com.rockville.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponse> getUsers() {
        return repository.getUsers();
    }

    @Override
    public List<UserResponse> getSalesAgents() {
        return repository.getSalesAgents()
                .stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .contactNumber(user.getContactNumber())
                        .firstName(user.getFirstName())
                        .middleName(user.getMiddleName())
                        .lastName(user.getLastName())
                        .createdBy(user.getCreatedBy())
                        .createdAt(user.getCreatedAt())
                        .updatedBy(user.getUpdatedBy())
                        .updatedAt(user.getUpdatedAt())
                        .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse createUser(UserRequest request) {
//        Saving to repository
        User user = repository.save(
                User.builder()
                        .username(request.getUsername())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .email(request.getEmail())
                        .contactNumber(request.getContactNumber())
                        .firstName(request.getFirstName())
                        .middleName(request.getMiddleName())
                        .lastName(request.getLastName())
                        .build()
        );
//        Return to controller
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .contactNumber(user.getContactNumber())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .build();
    }

    @Override
    public UserResponse updateUser(String userId, UserRequest request) {
        User user = repository.findByIdEquals(userId)
                .orElseThrow(RuntimeException::new);
        if (Optional.ofNullable(request.getUsername()).isPresent()) {
            user.setUsername(request.getUsername());
        }
        if (Optional.ofNullable(request.getPassword()).isPresent()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        if (Optional.ofNullable(request.getEmail()).isPresent()) {
            user.setEmail(request.getEmail());
        }
        if (Optional.ofNullable(request.getContactNumber()).isPresent()) {
            user.setContactNumber(request.getContactNumber());
        }
        if (Optional.ofNullable(request.getFirstName()).isPresent()) {
            user.setFirstName(request.getFirstName());
        }
        if (Optional.ofNullable(request.getMiddleName()).isPresent()) {
            user.setMiddleName(request.getMiddleName());
        }
        if (Optional.ofNullable(request.getLastName()).isPresent()) {
            user.setLastName(request.getLastName());
        }

        user = repository.save(user);

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .contactNumber(user.getContactNumber())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .build();
    }
}
