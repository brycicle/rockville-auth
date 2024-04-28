package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReservationRequest {

    private String lotId;
    private String customerId;
    private String contractPrice;

    private CustomerRequest customerRequest;

}
