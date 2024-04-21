package com.rockville.auth.repository;

import com.rockville.auth.model.domain.House;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface HouseRepository extends CrudRepository<House, String> {

    Optional<House> findByIdEquals(String id);
    Optional<House> findByTypeEquals(String type);
    Optional<House> findByFloorsEquals(String floors);
    Optional<House> findAllBySizeGreaterThanEqualAndSizeLessThanEqual(BigDecimal min, BigDecimal max);



}
