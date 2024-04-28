package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReservationRequest {

    String lotId;
    String customerId;
    String contractPrice;

}
