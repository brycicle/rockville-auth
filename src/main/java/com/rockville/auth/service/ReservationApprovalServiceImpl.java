package com.rockville.auth.service;

import com.rockville.auth.model.domain.ReservationApproval;
import com.rockville.auth.model.dto.ReservationApprovalRequest;
import com.rockville.auth.model.dto.ReservationApprovalResponse;
import com.rockville.auth.repository.ReservationApprovalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationApprovalServiceImpl implements ReservationApprovalService {

    private final ReservationApprovalRepository repository;

    @Override
    public List<ReservationApprovalResponse> getReservationApprovalByIdAndType(String reservationId, String type) {
        return repository.findAllByReservationIdEqualsAndTypeEquals(reservationId, type)
                .stream()
                .map(reservationApproval -> ReservationApprovalResponse.builder()
                        .reservationId(reservationApproval.getReservationId())
                        .type(reservationApproval.getType())
                        .isApproved(reservationApproval.getIsApproved())
                        .roleApprover(reservationApproval.getRoleApprover())
                        .createdBy(reservationApproval.getCreatedBy())
                        .createdAt(reservationApproval.getCreatedAt())
                        .updatedBy(reservationApproval.getUpdatedBy())
                        .updatedAt(reservationApproval.getUpdatedAt())
                        .build()
                )
                .toList();
    }

    @Override
    public ReservationApprovalResponse addReservationApproval(ReservationApprovalRequest request) {
        ReservationApproval reservationApproval = repository.save(
                ReservationApproval.builder()
                        .reservationId(request.getReservationId())
                        .type(request.getType())
                        .isApproved(request.getIsApproved())
                        .roleApprover(request.getRoleApprover())
                        .build()
        );

        return ReservationApprovalResponse.builder()
                .reservationId(reservationApproval.getReservationId())
                .type(reservationApproval.getType())
                .isApproved(reservationApproval.getIsApproved())
                .roleApprover(reservationApproval.getRoleApprover())
                .createdBy(reservationApproval.getCreatedBy())
                .createdAt(reservationApproval.getCreatedAt())
                .updatedBy(reservationApproval.getUpdatedBy())
                .updatedAt(reservationApproval.getUpdatedAt())
                .build();
    }
}
