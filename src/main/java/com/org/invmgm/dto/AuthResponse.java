package com.org.invmgm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponse {

    private int status;
    private String message;
    private String accessToken;
    private String tokenType = "Bearer";
    private long expiresIn;        // seconds
    private String username;
}

