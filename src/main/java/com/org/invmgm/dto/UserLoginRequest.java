package com.org.invmgm.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Email
    private String emailAddress;
    private String securityGroupId;
}
