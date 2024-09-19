package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentRequest {
    String paymentType;
    String paymentStatus;
}
