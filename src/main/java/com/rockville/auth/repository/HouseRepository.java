package com.rockville.auth.repository;

import com.rockville.auth.model.domain.House;
import com.rockville.auth.repository.qdsl.QdslHouseRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface HouseRepository extends CrudRepository<House, String>, QdslHouseRepository {

    Optional<House> findByIdEquals(String id);
    List<House> findAllByLotAreaGreaterThanEqualAndLotAreaLessThanEqual(BigDecimal min, BigDecimal max);

}
