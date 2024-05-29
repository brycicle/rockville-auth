package com.rockville.auth.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LotHistoryRequest {
    private String reservationId;
    private String action;
    private String type;
    private String description;
    private String customerId;
    private String userId;

    @AllArgsConstructor
    @Getter
    public enum LotHistoryAction
    {
        UPDATED("Updated"),
        RESERVED("Reserved");

        @Getter
        private final String action;
    }

    @AllArgsConstructor
    @Getter
    public enum LotHistoryType
    {
        LOT("Lot"),
        RESERVATION("Reservation");

        @Getter
        private final String type;
    }


    public static LotHistoryRequest.LotHistoryRequestBuilder fromActionAndType(
            LotHistoryAction action, LotHistoryType type
    ) {
        return fromAction(action).type(type.getType());
    }
    public static LotHistoryRequest.LotHistoryRequestBuilder fromAction(LotHistoryAction action) {
        return LotHistoryRequest.builder()
                .action(action.getAction());
    }

    public static LotHistoryRequest.LotHistoryRequestBuilder fromType(LotHistoryType type) {
        return LotHistoryRequest.builder()
                .type(type.getType());
    }
}
