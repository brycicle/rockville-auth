package com.rockville.auth.repository;

import com.rockville.auth.model.domain.LotHouse;
import com.rockville.auth.model.domain.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LotHouseRepository extends CrudRepository<LotHouse, String> {
    List<LotHouse> findAllByLotIdEquals(String lotId);
}
