package com.org.invmgm.dto;

import jakarta.validation.constraints.Email;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor      // ✅ allows new UserLoginResponse()
@AllArgsConstructor
@Builder
public class UserLoginResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdAt;
}