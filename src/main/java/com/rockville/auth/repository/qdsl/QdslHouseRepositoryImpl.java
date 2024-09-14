package com.rockville.auth.repository.qdsl;

import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rockville.auth.model.domain.House;
import com.rockville.auth.model.domain.HouseDetail;
import com.rockville.auth.model.domain.HousePicture;
import com.rockville.auth.model.dto.HouseDetailResponse;
import com.rockville.auth.model.dto.HousePictureResponse;
import com.rockville.auth.model.dto.HouseResponse;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.rockville.auth.model.domain.QHouse.house;
import static com.rockville.auth.model.domain.QHouseDetail.houseDetail;
import static com.rockville.auth.model.domain.QHousePicture.housePicture;


public class QdslHouseRepositoryImpl extends QuerydslRepositorySupport implements QdslHouseRepository {
    JPAQueryFactory queryFactory;

    @Autowired
    public QdslHouseRepositoryImpl(EntityManager entityManager) {
        super(House.class);
        setEntityManager(entityManager);
        queryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager);
    }

    @Override
    public List<HouseResponse> getHouses() {
        Set<House> houses = new HashSet<>();
        List<HouseDetail> houseDetails = new ArrayList<>();
        List<HousePicture> housePictures = new ArrayList<>();
        queryFactory.select(house, houseDetail, housePicture)
                .from(house)
                .join(houseDetail)
                .on(houseDetail.houseId.eq(house.id))
                .join(housePicture)
                .on(housePicture.houseId.eq(house.id))
                .stream()
                .forEach(tuple -> {
                    houses.add(tuple.get(house));
                    houseDetails.add(tuple.get(houseDetail));
                    housePictures.add(tuple.get(housePicture));
                });
        return houses.stream()
                .sorted(Comparator.comparing(House::getCreatedAt))
                .map(houseObj -> HouseResponse.builder()
                                .id(houseObj.getId())
                                .name(houseObj.getName())
                                .lotArea(houseObj.getLotArea())
                                .floorArea(houseObj.getFloorArea())
                                .price(houseObj.getPrice())
                                .multiplier(houseObj.getMultiplier().setScale(4, RoundingMode.UNNECESSARY))
                                .basePrice(houseObj.getBasePrice())
                                .details(
                                        houseDetails.stream()
                                                .filter(houseDetailObj -> houseDetailObj.getHouseId().equals(houseObj.getId()))
                                                .sorted(Comparator.comparing(HouseDetail::getCreatedAt))
                                                .map(houseDetailObj -> HouseDetailResponse.builder()
//                                                .houseId(houseDetailObj.getHouseId())
                                                                .detail(houseDetailObj.getDetails())
//                                                .createdBy(houseDetailObj.getCreatedBy())
//                                                .createdAt(houseDetailObj.getCreatedAt())
//                                                .updatedBy(houseDetailObj.getUpdatedBy())
//                                                .updatedAt(houseDetailObj.getUpdatedAt())
                                                                .build()
                                                )
                                                .collect(Collectors.toCollection(LinkedHashSet::new))
                                )
                                .pictures(
                                        housePictures.stream()
                                                .filter(housePictureObj -> housePictureObj.getHouseId().equals(houseObj.getId()))
                                                .sorted(Comparator.comparing(HousePicture::getCreatedAt))
                                                .map(housePictureObj -> HousePictureResponse.builder()
                                                        .image(housePictureObj.getImage())
                                                        .description(housePictureObj.getDescription())
                                                        .build())
                                                .collect(Collectors.toCollection(LinkedHashSet::new))
                                )
//                        .createdBy(houseObj.getCreatedBy())
//                        .createdAt(houseObj.getCreatedAt())
//                        .updatedBy(houseObj.getUpdatedBy())
//                        .updatedAt(houseObj.getUpdatedAt())
                                .build()
                )
                .toList();
    }

}
