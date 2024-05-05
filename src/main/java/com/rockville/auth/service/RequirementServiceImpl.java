package com.rockville.auth.service;

import com.rockville.auth.model.domain.Requirement;
import com.rockville.auth.model.dto.RequirementRequest;
import com.rockville.auth.model.dto.RequirementResponse;
import com.rockville.auth.repository.RequirementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RequirementServiceImpl implements RequirementService {

    private final RequirementRepository repository;

    @Override
    public RequirementResponse createRequirement(RequirementRequest request) {
        Requirement requirement = repository.save(
                Requirement.builder()
                        .reservationCode(request.getReservationCode())
                        .name(request.getName())
                        .build()
        );
        return RequirementResponse.builder()
                .reservationCode(requirement.getReservationCode())
                .name(requirement.getName())
                .createdBy(requirement.getCreatedBy())
                .createdAt(requirement.getCreatedAt())
                .updatedBy(requirement.getUpdatedBy())
                .updatedAt(requirement.getUpdatedAt())
                .build();
    }

    @Override
    public List<RequirementResponse> getRequirements(List<String> reservationCodes) {
        return repository.findAllByReservationCodeIn(reservationCodes)
                .stream()
                .map(requirement -> RequirementResponse.builder()
                        .reservationCode(requirement.getReservationCode())
                        .name(requirement.getName())
                        .createdBy(requirement.getCreatedBy())
                        .createdAt(requirement.getCreatedAt())
                        .updatedBy(requirement.getUpdatedBy())
                        .updatedAt(requirement.getUpdatedAt())
                        .build()
                ).toList();
    }

    @Override
    public RequirementResponse getRequirement(String reservationCode) {
        Requirement requirement = repository.findAllByReservationCodeEquals(reservationCode);
        return RequirementResponse.builder()
                .reservationCode(requirement.getReservationCode())
                .name(requirement.getName())
                .createdBy(requirement.getCreatedBy())
                .createdAt(requirement.getCreatedAt())
                .updatedBy(requirement.getUpdatedBy())
                .updatedAt(requirement.getUpdatedAt())
                .build();
    }
}
