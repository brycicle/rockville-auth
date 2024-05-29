package com.rockville.auth.repository;

import com.rockville.auth.model.domain.Reservation;
import com.rockville.auth.model.domain.Role;
import com.rockville.auth.repository.qdsl.QdslReservationRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, String>, QdslReservationRepository {
    List<Reservation> findAllByIdIsNotNull();
    Reservation findByIdEquals(String Reservation);
}
