package com.rockville.auth.repository;

import com.rockville.auth.model.domain.LotHistory;
import com.rockville.auth.repository.qdsl.QdslHouseRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotHistoryRepository extends CrudRepository<LotHistory, String>, QdslHouseRepository {
    List<LotHistory> findAllByLotIdEquals(String lotId);
}
