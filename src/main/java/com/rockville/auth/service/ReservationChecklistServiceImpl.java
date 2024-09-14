package com.rockville.auth.service;

import com.rockville.auth.model.domain.ReservationChecklist;
import com.rockville.auth.model.dto.RequirementResponse;
import com.rockville.auth.model.dto.ReservationChecklistRequest;
import com.rockville.auth.model.dto.ReservationChecklistResponse;
import com.rockville.auth.repository.ReservationChecklistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        List<ReservationChecklist> checklists = Objects.nonNull(type) ? repository.findAllByTypeEquals(type) : repository.findAllByIdIsNotNull();
        List<RequirementResponse> requirements = requirementService.getRequirements(
                checklists.stream().map(ReservationChecklist::getRequirementCode).toList()
        );
        return checklists.stream()
                .map(reservationChecklist -> {
                            RequirementResponse requirementResponse = requirements.stream()
                                    .filter(requirement -> requirement.getReservationCode()
                                            .equals(reservationChecklist.getRequirementCode()))
                                    .findFirst()
                                    .orElse(null);
                    return ReservationChecklistResponse.builder()
                            .type(reservationChecklist.getType())
                            .requirementCode(reservationChecklist.getRequirementCode())
                            .requirementName(
                                    Objects.nonNull(requirementResponse) ?
                                            requirementResponse.getName() : null
                            )
//                        .requirement(requirementService.getRequirement(reservationChecklist.getRequirementCode()))
                            .requirement(requirementResponse)
                            .createdBy(reservationChecklist.getCreatedBy())
                            .createdAt(reservationChecklist.getCreatedAt())
                            .updatedBy(reservationChecklist.getUpdatedBy())
                            .updatedAt(reservationChecklist.getUpdatedAt())
                            .build();
                }
                )
                .sorted(Comparator.comparing(ReservationChecklistResponse::getCreatedAt))
                .toList();
    }
}
