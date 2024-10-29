package com.rockville.auth.repository;

import com.rockville.auth.model.domain.PaymentTerm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentTermRepository extends CrudRepository<PaymentTerm, String> {
    Optional<PaymentTerm> findByReservationIdEquals(String reservationId);

}
