package com.rockville.auth.service;

import com.rockville.auth.model.domain.Reservation;
import com.rockville.auth.model.dto.*;
import com.rockville.auth.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final LotService lotService;
    private final CustomerService customerService;
    private final HouseService houseService;
    private final ReservationDocumentService reservationDocumentService;
    private final ReservationHistoryService reservationHistoryService;
    private final LotHistoryService lotHistoryService;

    @Override
    @Transactional
    public ReservationResponse createReservation(ReservationRequest request) {
        LotResponse lot = lotService.findLotByLotNameAndBlockName(request.getLotName(), request.getBlockName());

        CustomerResponse customer = customerService.createCustomer(
                CustomerRequest.builder()
                        .firstName(request.getFirstName())
                        .middleName(request.getMiddleName())
                        .lastName(request.getLastName())
                        .suffix(request.getSuffix())
                        .gender(request.getGender())
                        .maritalStatus(request.getMaritalStatus())
                        .type(request.getType())
                        .emailAddress(request.getEmailAddress())
                        .contactNumber(request.getContactNumber())
                        .address(request.getAddress())
                        .build()
        );

        HouseResponse house = houseService.findHouseByName(request.getHouseName());

        Reservation reservation = reservationRepository.save(
                Reservation.builder()
                        .lotId(lot.getId())
                        .customerId(customer.getId())
                        .houseId(house.getId())
                        .lotPrice(request.getLotPrice())
                        .housePrice(request.getHousePrice())
                        .multiplier(request.getMultiplier())
                        .status("For Approval")
                        .expiration(Instant.now().plus(30, ChronoUnit.DAYS))
                        .agentName(request.getAgentName())
                        .build()
        );

        ReservationDocumentResponse resFee = reservationDocumentService.addReservationDocument(
                ReservationDocumentRequest.builder()
                        .reservationId(reservation.getId())
                        .requirementCode("RES_FEE")
                        .file(request.getReservationResFee())
                        .build()
        );

        ReservationDocumentResponse govId = reservationDocumentService.addReservationDocument(
                ReservationDocumentRequest.builder()
                        .reservationId(reservation.getId())
                        .requirementCode("GOV_ID")
                        .file(request.getReservationGovId())
                        .build()
        );

        reservationHistoryService.saveReservationHistory(
                ReservationHistoryRequest.fromActionAndType(
                                ReservationHistoryRequest.ReservationHistoryAction.CREATED,
                                ReservationHistoryRequest.ReservationHistoryType.RESERVATION
                        )
                        .reservationId(reservation.getId())
                        .build()
        );
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        reservationHistoryService.saveReservationHistory(
                ReservationHistoryRequest.fromActionAndType(
                                ReservationHistoryRequest.ReservationHistoryAction.UPLOADED,
                                ReservationHistoryRequest.ReservationHistoryType.RESERVATION_DOCUMENT
                        )
                        .reservationId(reservation.getId())
                        .reservationDocumentId(govId.getId())
                        .build()
        );
        reservationHistoryService.saveReservationHistory(
                ReservationHistoryRequest.fromActionAndType(
                                ReservationHistoryRequest.ReservationHistoryAction.UPLOADED,
                                ReservationHistoryRequest.ReservationHistoryType.RESERVATION_DOCUMENT
                        )
                        .reservationId(reservation.getId())
                        .reservationDocumentId(resFee.getId())
                        .build()
        );

        lot = lotService.updateLot(
                lot.getId(),
                LotRequest.builder()
                        .status("Reserved")
                        .type(lot.getType())
                        .build()
        );

        lotHistoryService.saveLotHistory(
                LotHistoryRequest.fromActionAndType(
                        LotHistoryRequest.LotHistoryAction.RESERVED,
                        LotHistoryRequest.LotHistoryType.RESERVATION
                ).build()
        );

        return ReservationResponse.builder()
                .id(reservation.getId())
                .lotPrice(reservation.getLotPrice())
                .housePrice(reservation.getHousePrice())
                .status(reservation.getStatus())
                .expiration(reservation.getExpiration())
                .customer(customer)
                .house(house)
                .lot(lot)
                .documents(Set.of(govId, resFee))
                .createdBy(reservation.getCreatedBy())
                .createdAt(reservation.getCreatedAt())
                .updatedBy(reservation.getUpdatedBy())
                .updatedAt(reservation.getUpdatedAt())
                .build();
    }

    @Override
    public Set<ReservationResponse> getReservations(UserDetailsDto user) {
        return reservationRepository.getReservationsByRole(user);
    }

    @Override
    public Set<ReservationDocumentResponse> getReservationDocuments(UserDetailsDto user, String reservationId) {
        return reservationDocumentService.getReservationDocuments(reservationId);
    }
}
