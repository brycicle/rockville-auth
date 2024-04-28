package com.rockville.auth.repository;

import com.rockville.auth.model.domain.HouseDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface HouseDetailRepository extends CrudRepository<HouseDetail, String> {
    List<HouseDetail> findAllByHouseIdEquals(String houseId);
    Optional<HouseDetail> findByIdEquals(String id);

}
