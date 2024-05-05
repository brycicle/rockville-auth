package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class CustomerRequest {

    String firstName;
    String middleName;
    String lastName;
    String suffix;
    String contactNumber;
    String emailAddress;
    String address;
    String type;

}
