package com.rockville.auth.repository.qdsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rockville.auth.model.domain.Lot;
import com.rockville.auth.model.domain.LotCoordinate;
import com.rockville.auth.model.domain.LotHouse;
import com.rockville.auth.model.domain.LotType;
import com.rockville.auth.model.dto.LotCoordinateResponse;
import com.rockville.auth.model.dto.LotHouseResponse;
import com.rockville.auth.model.dto.LotResponse;
import com.rockville.auth.model.dto.LotTypeResponse;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.rockville.auth.model.domain.QLot.lot;
import static com.rockville.auth.model.domain.QLotCoordinate.lotCoordinate;
import static com.rockville.auth.model.domain.QLotHouse.lotHouse;
import static com.rockville.auth.model.domain.QLotType.lotType1;

public class QdslLotRepositoryImpl extends QuerydslRepositorySupport implements QdslLotRepository {
    JPAQueryFactory queryFactory;
    @Autowired
    public QdslLotRepositoryImpl(EntityManager entityManager) {
        super(Lot.class);
        setEntityManager(entityManager);
        queryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager);
    }

    @Override
    public List<LotResponse> getLots(String status) {
        Set<Lot> lots = new HashSet<>();
        List<LotCoordinate> lotCoordinates = new ArrayList<>();
        List<LotHouse> lotHouses = new ArrayList<>();
        List<LotType> lotTypes = queryFactory.select(lotType1)
                .from(lotType1)
                .stream()
                .toList();
        BooleanBuilder builder = new BooleanBuilder().and(
                lot.id.eq(lotCoordinate.lotId)
        );

        if (!ObjectUtils.isEmpty(status)) {
            builder.and(lot.status.eq(status));
        }
        queryFactory.select(lot, lotCoordinate, lotHouse)
                .from(lot)
                .join(lotCoordinate)
                .on(lotCoordinate.lotId.eq(lot.id))
                .join(lotHouse)
                .on(lotHouse.lotId.eq(lot.id))
                .where(builder)
                .stream()
                .forEach(tuple -> {
                    lots.add(tuple.get(lot));
                    lotCoordinates.add(tuple.get(lotCoordinate));
                    lotHouses.add(tuple.get(lotHouse));
                });
        return lots.stream()
                .map(lotObj -> LotResponse.builder()
                        .id(lotObj.getId())
                        .blockName(lotObj.getBlockName())
                        .lotName(lotObj.getLotName())
                        .status(lotObj.getStatus())
                        .size(lotObj.getSize())
                        .blockLotName("B" + lotObj.getBlockName().replace("Block ", "") + "L" + lotObj.getLotName().replace("Lot ", ""))
                        .lotAvailability(
                                ObjectUtils.isEmpty(lotObj.getLotAvailability())
                        ? Instant.MAX : lotObj.getLotAvailability())
                        .coordinates(
                                lotCoordinates.stream()
                                        .filter(lotCoordinate1 -> lotCoordinate1.getLotId().equals(lotObj.getId()))
                                        .sorted(Comparator.comparing(LotCoordinate::getCreatedAt))
                                        .map(lotCoordinate -> LotCoordinateResponse.builder()
                                                .coorX(lotCoordinate.getCoorX())
                                                .coorY(lotCoordinate.getCoorY())
                                                .build()
                                        )
                                        .collect(Collectors.toCollection(LinkedHashSet::new))
                        )
                        .lotHouses(
                                lotHouses.stream()
                                        .filter(lotHouse -> lotHouse.getLotId().equals(lotObj.getId()))
                                        .sorted(Comparator.comparing(LotHouse::getCreatedAt))
                                        .map(lotHouse -> LotHouseResponse.builder()
                                                .houseName(lotHouse.getHouseName())
                                                .build()
                                        )
                                        .collect(Collectors.toCollection(LinkedHashSet::new))
                        )
                        .lotType(
                                lotTypes.stream()
                                        .filter(lotType -> lotType.getLotType().equals(lotObj.getType()))
                                        .map(lotType -> LotTypeResponse.builder()
                                                .price(lotType.getPrice())
                                                .lotType(lotType.getLotType())
                                                .build())
                                        .findFirst()
                                        .orElse(null)
                        )
                        .build()
                )
                .toList();
    }
}
