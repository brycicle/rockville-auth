package com.rockville.auth.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponse {
    private String id;
    private BigDecimal lotPrice;
    private BigDecimal housePrice;
    private BigDecimal multiplier;
    private String status;
    private String agentName;
    private Instant expiration;

    private CustomerResponse customer;
    private HouseResponse house;
    private Set<ReservationDocumentResponse> documents;
    private LotResponse lot;

    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;
}
