package com.rockville.auth.repository;

import com.rockville.auth.model.domain.LotCoordinate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LotCoordinateRepository extends CrudRepository<LotCoordinate, String> {

    List<LotCoordinate> findAllByLotIdEquals(String lotId);


}
