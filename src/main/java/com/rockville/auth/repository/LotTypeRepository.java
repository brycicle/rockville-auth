package com.rockville.auth.repository;

import com.rockville.auth.model.domain.LotType;
import org.springframework.data.repository.CrudRepository;

public interface LotTypeRepository extends CrudRepository<LotType, String> {
    LotType findByLotTypeEquals(String lotType);
}
