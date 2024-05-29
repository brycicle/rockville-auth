package com.rockville.auth.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Set;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ReservationHistoryResponse {
    private String id;
    private String reservationId;
    private String action;
    private String type;
    private String description;
    private String houseId;
    private String lotId;
    private String reservationDocumentId;
    private String reservationApprovalId;

    private CustomerResponse customer;
    private HouseResponse house;
    private ReservationDocumentResponse document;
    private LotResponse lot;
    private Set<ReservationApprovalResponse> approvals;

    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;
}
