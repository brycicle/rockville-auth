package com.rockville.auth.repository;

import com.rockville.auth.model.domain.ReservationHistory;
import com.rockville.auth.repository.qdsl.QdslHouseRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationHistoryRepository extends CrudRepository<ReservationHistory, String>, QdslHouseRepository {
    List<ReservationHistory> findAllByReservationIdEquals(String reservationId);
}
