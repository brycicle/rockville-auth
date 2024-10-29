package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data
public class PaymentTermResponse {
    private String id;
    private String reservationId;
    private String code;
    private String name;
    private Integer terms;
    private String monthsWithoutInterest;
    private Double interest;
    private Double priceWithoutInterest;
    private Double totalPrice;
    private Instant startDate;
    private Instant endDate;

    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;
}
