package com.rockville.auth.repository;

import com.rockville.auth.model.domain.ReservationChecklist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationChecklistRepository extends CrudRepository<ReservationChecklist, String> {
    List<ReservationChecklist> findAllByTypeEquals(String type);
    List<ReservationChecklist> findAllByIdIsNotNull();
}
