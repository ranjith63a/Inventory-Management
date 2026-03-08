package com.org.invmgm.controller;

import com.org.invmgm.dto.UserLoginRequest;
import com.org.invmgm.dto.UserLoginResponse;
import com.org.invmgm.service.impl.UserLoginServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserLoginController {

    private final UserLoginServiceImpl useSer;

    @PostMapping
    ResponseEntity<UserLoginResponse> create(UserLoginRequest request) {
        UserLoginResponse response = useSer.registerUser(request);
        return ResponseEntity.ok(response);
    }
}
