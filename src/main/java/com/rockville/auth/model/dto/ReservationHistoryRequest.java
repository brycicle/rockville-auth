package com.rockville.auth.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ReservationHistoryRequest {
    private String reservationId;
    private String action;
    private String type;
    private String description;
    private String houseId;
    private String lotId;
    private String reservationDocumentId;
    private String reservationApprovalId;

    @AllArgsConstructor
    @Getter
    public enum ReservationHistoryAction
    {
        CREATED("Created"),
        UPDATED("Updated"),
        UPLOADED("Uploaded"),
        ENDORSED("Endorsed"),
        APPROVED("Approved"),
        CANCELLED("Cancelled"),
        EXTENDED("Extended");

        @Getter
        private final String action;
    }

    @AllArgsConstructor
    @Getter
    public enum ReservationHistoryType
    {
        RESERVATION("Reservation"),
        HOUSE("House"),
        LOT("Lot"),
        RESERVATION_DOCUMENT("Reservation Document"),
        APPROVAL("Reservation Approval");

        @Getter
        private final String type;
    }


    public static ReservationHistoryRequestBuilder fromActionAndType(
            ReservationHistoryAction action, ReservationHistoryType type
    ) {
        return fromAction(action).type(type.getType());
    }
    public static ReservationHistoryRequestBuilder fromAction(ReservationHistoryAction action) {
        return ReservationHistoryRequest.builder()
                .action(action.getAction());
    }

    public static ReservationHistoryRequestBuilder fromType(ReservationHistoryType type) {
        return ReservationHistoryRequest.builder()
                .type(type.getType());
    }
}
