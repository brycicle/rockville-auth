package com.rockville.auth.service;

import com.rockville.auth.model.domain.Role;
import com.rockville.auth.model.dto.RoleRequest;
import com.rockville.auth.model.dto.RoleResponse;
import com.rockville.auth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    @Override
    public List<RoleResponse> getRoles() {
        return repository.findAllByIdIsNotNull()
                .stream()
                .map(role -> RoleResponse.builder()
                        .id(role.getId())
                        .userId(role.getUserId())
                        .name(role.getName())
                        .createdBy(role.getCreatedBy())
                        .createdAt(role.getCreatedAt())
                        .updatedBy(role.getUpdatedBy())
                        .updatedAt(role.getUpdatedAt())
                        .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public RoleResponse createRole(RoleRequest request) {
        Role role = repository.save(
                Role.builder()
                        .id(request.getId())
                        .userId(request.getUserId())
                        .name("ADMIN")
                        .build()
        );

        return RoleResponse.builder()
                .id(role.getId())
                .userId(role.getUserId())
                .name(role.getName())
                .createdBy(role.getCreatedBy())
                .createdAt(role.getCreatedAt())
                .updatedBy(role.getUpdatedBy())
                .updatedAt(role.getUpdatedAt())
                .build();
    }
}
