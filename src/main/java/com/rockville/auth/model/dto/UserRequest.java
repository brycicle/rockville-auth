package com.rockville.auth.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserRequest {

    private String username;
    private String password;
    private String email;
    private String contactNumber;
    private String firstName;
    private String middleName;
    private String lastName;

}
