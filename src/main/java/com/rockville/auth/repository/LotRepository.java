package com.rockville.auth.repository;

import com.rockville.auth.model.domain.Lot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface LotRepository extends CrudRepository<Lot, String> {

    Optional<Lot> findByIdEquals(String id);
    Optional<Lot> findByBlockNameEquals(String blockName);
    Optional<Lot> findByLotNameEquals();
    List<Lot> findAllByStatusEquals(String status);
    Optional<Lot> findAllBySizeGreaterThanEqualAndSizeLessThanEqual(BigDecimal min, BigDecimal max);



}
