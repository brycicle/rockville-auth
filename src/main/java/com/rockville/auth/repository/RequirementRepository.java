package com.rockville.auth.repository;

import com.rockville.auth.model.domain.Requirement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RequirementRepository extends CrudRepository<Requirement, String> {
    List<Requirement> findAllByReservationCodeIn(List<String> reservationCodes);
    Requirement findAllByReservationCodeEquals(String reservationCode);
}
