package com.rockville.auth.repository.qdsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rockville.auth.model.domain.Reservation;
import com.rockville.auth.model.dto.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.security.core.GrantedAuthority;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rockville.auth.model.domain.QCustomer.customer;
import static com.rockville.auth.model.domain.QHouse.house;
import static com.rockville.auth.model.domain.QLot.lot;
import static com.rockville.auth.model.domain.QReservation.reservation;
import static com.rockville.auth.model.domain.QReservationDocument.reservationDocument;

public class QdslReservationRepositoryImpl extends QuerydslRepositorySupport implements QdslReservationRepository {
    JPAQueryFactory queryFactory;

    @Autowired
    public QdslReservationRepositoryImpl(EntityManager entityManager) {
        super(Reservation.class);
        setEntityManager(entityManager);
        queryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager);
    }

    @Override
    public Set<ReservationResponse> getReservationsByRole(UserDetailsDto user) {
        BooleanBuilder builder = new BooleanBuilder();
        if (user.getUser().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList().contains("Sales_Agent")) {
            builder.and(reservation.createdBy.eq(user.getUsername()));
        }
        Set<ReservationResponse> reservations = queryFactory.select(
                        reservation, customer, house, lot
                )
                .from(reservation)
                .join(customer)
                .on(customer.id.eq(reservation.customerId))
                .join(house)
                .on(house.id.eq(reservation.houseId))
                .join(lot)
                .on(lot.id.eq(reservation.lotId))
                .where(builder)
                .stream()
                .map(tuple ->
                        ReservationResponse.builder()
                                .id(Objects.requireNonNull(tuple.get(reservation)).getId())
                                .lotPrice(Objects.requireNonNull(tuple.get(reservation)).getLotPrice())
                                .housePrice(Objects.requireNonNull(tuple.get(reservation)).getHousePrice())
                                .status(Objects.requireNonNull(tuple.get(reservation)).getStatus())
                                .expiration(Objects.requireNonNull(tuple.get(reservation)).getExpiration())
                                .multiplier(Objects.requireNonNull(tuple.get(reservation)).getMultiplier())
                                .customer(CustomerResponse.builder()
                                        .id(Objects.requireNonNull(tuple.get(customer)).getId())
                                        .firstName(Objects.requireNonNull(tuple.get(customer)).getFirstName())
                                        .middleName(Objects.requireNonNull(tuple.get(customer)).getMiddleName())
                                        .lastName(Objects.requireNonNull(tuple.get(customer)).getLastName())
                                        .suffix(Objects.requireNonNull(tuple.get(customer)).getSuffix())
                                        .gender(Objects.requireNonNull(tuple.get(customer)).getGender())
                                        .maritalStatus(Objects.requireNonNull(tuple.get(customer)).getMaritalStatus())
                                        .emailAddress(Objects.requireNonNull(tuple.get(customer)).getEmailAddress())
                                        .contactNumber(Objects.requireNonNull(tuple.get(customer)).getContactNumber())
                                        .address(Objects.requireNonNull(tuple.get(customer)).getAddress())
                                        .type(Objects.requireNonNull(tuple.get(customer)).getType())
                                        .build()
                                )
                                .house(HouseResponse.builder()
                                        .id(Objects.requireNonNull(tuple.get(house)).getId())
                                        .name(Objects.requireNonNull(tuple.get(house)).getName())
                                        .lotArea(Objects.requireNonNull(tuple.get(house)).getLotArea())
                                        .floorArea(Objects.requireNonNull(tuple.get(house)).getFloorArea())
                                        .build())
                                .lot(LotResponse.builder()
                                        .id(Objects.requireNonNull(tuple.get(lot)).getId())
                                        .blockName(Objects.requireNonNull(tuple.get(lot)).getBlockName())
                                        .lotName(Objects.requireNonNull(tuple.get(lot)).getLotName())
                                        .status(Objects.requireNonNull(tuple.get(lot)).getStatus())
                                        .size(Objects.requireNonNull(tuple.get(lot)).getSize())
                                        .type(Objects.requireNonNull(tuple.get(lot)).getType())
                                        .build()
                                )
                                .agentName(Objects.requireNonNull(tuple.get(reservation)).getAgentName())
                                .createdBy(Objects.requireNonNull(tuple.get(reservation)).getCreatedBy())
                                .createdAt(Objects.requireNonNull(tuple.get(reservation)).getCreatedAt())
                                .updatedBy(Objects.requireNonNull(tuple.get(reservation)).getUpdatedBy())
                                .updatedAt(Objects.requireNonNull(tuple.get(reservation)).getUpdatedAt())
                                .build()
                )
                .sorted(Comparator.comparing(ReservationResponse::getExpiration))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        Set<ReservationDocumentResponse> documents = queryFactory.select(reservationDocument)
                .from(reservationDocument)
                .where(reservationDocument.reservationId.in(reservations.stream()
                        .map(ReservationResponse::getId)
                        .collect(Collectors.toSet())))
                .stream()
                .map(reservationDocument1 -> ReservationDocumentResponse.builder()
                        .id(reservationDocument1.getId())
                        .reservationId(reservationDocument1.getReservationId())
                        .filePath(reservationDocument1.getFilePath())
                        .fileType(reservationDocument1.getFileType())
                        .requirementCode(reservationDocument1.getRequirementCode())
                        .build())
                .collect(Collectors.toSet());
        return reservations.stream()
                .peek(reservation -> reservation.setDocuments(documents.stream()
                        .filter(document -> document.getReservationId().equals(reservation.getId()))
                        .collect(Collectors.toSet()))
                )
                .collect(Collectors.toSet());
    }
}
