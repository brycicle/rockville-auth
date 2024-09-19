package com.rockville.auth.service;

import com.rockville.auth.model.domain.Payment;
import com.rockville.auth.model.domain.Requirement;
import com.rockville.auth.model.domain.Reservation;
import com.rockville.auth.model.dto.HouseResponse;
import com.rockville.auth.model.dto.PaymentRequest;
import com.rockville.auth.model.dto.PaymentResponse;
import com.rockville.auth.repository.PaymentRepository;
import com.rockville.auth.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repository;
    private final ReservationRepository reservationRepository;

    @Override
    public PaymentResponse createPayment(String reservationId, PaymentRequest request) {
        Reservation reservation = reservationRepository.findByIdEquals(reservationId).orElseThrow(RuntimeException::new);
        Payment payment = repository.save(
                Payment.builder()
                        .reservationId(reservation.getId())
                        .customerId(reservation.getCustomerId())
                        .paymentType(request.getPaymentType())
                        .paymentStatus(request.getPaymentStatus())
                .build()
        );

        return PaymentResponse.builder()
                .id(payment.getId())
                .reservationId(payment.getReservationId())
                .customerId(payment.getCustomerId())
                .paymentType(payment.getPaymentType())
                .paymentStatus(payment.getPaymentStatus())
                .build();
    }

    @Override
    public PaymentResponse getPayment(String id){
        Payment payment = repository.findByIdEquals(id).orElseThrow(RuntimeException::new);
        return PaymentResponse.builder()
                .id(payment.getId())
                .reservationId(payment.getReservationId())
                .customerId(payment.getCustomerId())
                .paymentType(payment.getPaymentType())
                .paymentStatus(payment.getPaymentStatus())
                .build();
    }

    @Override
    public PaymentResponse getPaymentByReservationId(String reservationId) {
        Payment payment = repository.findByReservationIdEquals(reservationId).orElseThrow(RuntimeException::new);
        return PaymentResponse.builder()
                .id(payment.getId())
                .reservationId(payment.getReservationId())
                .customerId(payment.getCustomerId())
                .paymentType(payment.getPaymentType())
                .paymentStatus(payment.getPaymentStatus())
                .build();
    }
}
