package com.rockville.auth.service;

import com.rockville.auth.model.dto.CustomerRequest;
import com.rockville.auth.model.dto.PaymentTermResponse;


public interface PaymentTermService {
    PaymentTermResponse getPaymentTerm(String reservationId);
    PaymentTermResponse createPaymentTerm(String reservationId, CustomerRequest request);
    PaymentTermResponse updatePaymentTerm(String reservationId, CustomerRequest request);
}
