package com.rockville.auth.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ReservationRequest {
    private MultipartFile reservationResFee;
    private MultipartFile reservationGovId;
    private String lotName;
    private String blockName;
    private BigDecimal lotPrice;
    private String houseName;
    private BigDecimal housePrice;
    private BigDecimal multiplier;
    private String firstName;
    private String middleName;
    private String lastName;
    private String suffix;
    private String gender;
    private String maritalStatus;
    private String type;
    private String emailAddress;
    private String contactNumber;
    private String address;
    private String agentName;
}
