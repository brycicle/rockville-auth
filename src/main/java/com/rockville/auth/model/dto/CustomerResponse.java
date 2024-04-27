package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Builder
@Data
public class CustomerResponse {
    private String id;
    String firstName;
    String middleName;
    String lastName;
    String suffix;
    String contactNumber;
    String emailAddress;
    String address;

    private List<LotCoordinateResponse> coordinates;

    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;
}
