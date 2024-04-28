package com.rockville.auth.repository;

import com.rockville.auth.model.domain.Customer;
import com.rockville.auth.model.domain.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, String> {
    Optional<Reservation> findAllByIdIsNotNull();
    Optional<Reservation> findByLotIdEquals(String lotId);
    Optional<Reservation> findByCustomerId(String customerId);
    Optional<Reservation> findByContractPrice(String contractPrice);

}
