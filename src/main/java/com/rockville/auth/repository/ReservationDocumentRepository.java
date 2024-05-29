package com.rockville.auth.repository;

import com.rockville.auth.model.domain.ReservationDocument;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationDocumentRepository extends CrudRepository<ReservationDocument, String> {
    List<ReservationDocument> findAllByReservationIdEquals(String reservationId);
    ReservationDocument findByIdEquals(String id);
}
