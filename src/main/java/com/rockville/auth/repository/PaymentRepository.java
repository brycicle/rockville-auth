package com.rockville.auth.repository;

import com.rockville.auth.model.domain.Payment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends CrudRepository<Payment, String> {
    Optional<Payment> findByIdEquals(String id);

    Optional<Payment> findByPaymentTypeEquals(String paymentType);

    Optional<Payment> findByPaymentStatusEquals(String paymentStatus);

    Optional<Payment> findByReservationIdEquals(String reservationId);

}
