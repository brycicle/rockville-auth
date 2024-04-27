package com.rockville.auth.repository.qdsl;

import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rockville.auth.model.domain.Lot;
import com.rockville.auth.model.domain.LotCoordinate;
import com.rockville.auth.model.dto.LotCoordinateResponse;
import com.rockville.auth.model.dto.LotResponse;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.*;
import java.util.stream.Collectors;

import static com.rockville.auth.model.domain.QLot.lot;
import static com.rockville.auth.model.domain.QLotCoordinate.lotCoordinate;

public class QdslLotRepositoryImpl extends QuerydslRepositorySupport implements QdslLotRepository {
    JPAQueryFactory queryFactory;
    @Autowired
    public QdslLotRepositoryImpl(EntityManager entityManager) {
        super(Lot.class);
        setEntityManager(entityManager);
        queryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager);
    }

    @Override
    public List<LotResponse> getLots() {
        Set<Lot> lots = new HashSet<>();
        List<LotCoordinate> lotCoordinates = new ArrayList<>();
        queryFactory.select(lot, lotCoordinate)
                .from(lot)
                .join(lotCoordinate)
                .on(lotCoordinate.lotId.eq(lot.id))
                .where(lot.id.eq(lotCoordinate.lotId))
                .stream()
                .forEach(tuple -> {
                    lots.add(tuple.get(lot));
                    lotCoordinates.add(tuple.get(lotCoordinate));
                });
        return lots.stream()
                .map(lotObj -> LotResponse.builder()
                        .id(lotObj.getId())
                        .blockName(lotObj.getBlockName())
                        .lotName(lotObj.getLotName())
                        .status(lotObj.getStatus())
                        .size(lotObj.getSize())
                        .coordinates(
                                lotCoordinates.stream()
                                        .filter(lotCoordinate1 -> lotCoordinate1.getLotId().equals(lotObj.getId()))
                                        .map(lotCoordinate -> LotCoordinateResponse.builder()
                                                .lotId(lotCoordinate.getLotId())
                                                .coorX(lotCoordinate.getCoorX())
                                                .coorY(lotCoordinate.getCoorY())
                                                .createdAt(lotCoordinate.getCreatedAt())
                                                .build()
                                        )
                                        .sorted(Comparator.comparing(LotCoordinateResponse::getCreatedAt))
                                        .collect(Collectors.toList())
                        )
                        .createdBy(lotObj.getCreatedBy())
                        .createdAt(lotObj.getCreatedAt())
                        .updatedBy(lotObj.getUpdatedBy())
                        .updatedAt(lotObj.getUpdatedAt())
                        .build()
                )
                .toList();
    }
}
