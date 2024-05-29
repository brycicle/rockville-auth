package com.rockville.auth.repository;

import com.rockville.auth.model.domain.Lot;
import com.rockville.auth.repository.qdsl.QdslLotRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface LotRepository extends CrudRepository<Lot, String>, QdslLotRepository {

    Optional<Lot> findByIdEquals(String id);
    Optional<Lot> findByBlockNameEquals(String blockName);
    Optional<Lot> findByLotNameEqualsAndBlockNameEquals(String lotName, String blockName);
    List<Lot> findAllByStatusEquals(String status);
    List<Lot> findAllByIdIsNotNull();
    Optional<Lot> findAllBySizeGreaterThanEqualAndSizeLessThanEqual(BigDecimal min, BigDecimal max);
}