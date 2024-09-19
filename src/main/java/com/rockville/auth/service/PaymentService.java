package com.rockville.auth.service;

import com.rockville.auth.model.dto.PaymentRequest;
import com.rockville.auth.model.dto.PaymentResponse;

import java.util.List;


public interface PaymentService {
    PaymentResponse createPayment (String reservationId, PaymentRequest request);
    PaymentResponse getPayment (String id);
    PaymentResponse getPaymentByReservationId(String reservationId);

}
