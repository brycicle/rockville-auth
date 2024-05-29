package com.rockville.auth.repository;

import com.rockville.auth.model.domain.ReservationApproval;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationApprovalRepository extends CrudRepository<ReservationApproval, String> {
    List<ReservationApproval> findAllByReservationIdEquals(String reservationId);
    List<ReservationApproval> findAllByReservationIdEqualsAndTypeEquals(String reservationId, String type);
}
