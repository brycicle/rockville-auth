package com.rockville.auth.service;

import com.rockville.auth.model.domain.ReservationChecklist;
import com.rockville.auth.model.dto.ReservationChecklistRequest;
import com.rockville.auth.model.dto.ReservationChecklistResponse;
import com.rockville.auth.repository.ReservationChecklistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationChecklistServiceImpl implements ReservationChecklistService {

    private final ReservationChecklistRepository repository;
    private final RequirementService requirementService;

    @Override
    public ReservationChecklistResponse createReservationChecklist(ReservationChecklistRequest request) {
        ReservationChecklist reservationChecklist = repository.save(
                ReservationChecklist.builder()
                        .type(request.getType())
                        .requirementCode(request.getRequirementCode())
                        .build()
        );
        return ReservationChecklistResponse.builder()
                .type(reservationChecklist.getType())
                .requirementCode(reservationChecklist.getRequirementCode())
                .requirement(requirementService.getRequirement(reservationChecklist.getRequirementCode()))
                .createdBy(reservationChecklist.getCreatedBy())
                .createdAt(reservationChecklist.getCreatedAt())
                .updatedBy(reservationChecklist.getUpdatedBy())
                .updatedAt(reservationChecklist.getUpdatedAt())
                .build();
    }

    @Override
    public List<ReservationChecklistResponse> getReservationChecklist(String type) {
        return repository.findAllByTypeEquals(type)
                .stream()
                .map(reservationChecklist -> ReservationChecklistResponse.builder()
                        .type(reservationChecklist.getType())
                        .requirementCode(reservationChecklist.getRequirementCode())
                        .requirement(requirementService.getRequirement(reservationChecklist.getRequirementCode()))
                        .createdBy(reservationChecklist.getCreatedBy())
                        .createdAt(reservationChecklist.getCreatedAt())
                        .updatedBy(reservationChecklist.getUpdatedBy())
                        .updatedAt(reservationChecklist.getUpdatedAt())
                        .build())
                .toList();
    }
}
