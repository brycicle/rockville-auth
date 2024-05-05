package com.rockville.auth.repository;

import com.rockville.auth.model.domain.HousePicture;
import com.rockville.auth.repository.qdsl.QdslHouseRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HousePictureRepository extends CrudRepository<HousePicture, String>, QdslHouseRepository {
    List<HousePicture> findAllByHouseIdEquals(String houseId);
}
