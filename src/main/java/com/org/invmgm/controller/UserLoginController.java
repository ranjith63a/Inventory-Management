package com.org.invmgm.controller;

import com.org.invmgm.dto.UserLoginRequest;
import com.org.invmgm.dto.UserLoginResponse;
import com.org.invmgm.service.impl.UserLoginServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserLoginController {

    private final UserLoginServiceImpl useSer;

    @PostMapping
    ResponseEntity<UserLoginResponse> create(@Valid @RequestBody UserLoginRequest request) {
        UserLoginResponse response = useSer.registerUser(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/username/{username}")
    ResponseEntity<UserLoginResponse> getByUsername(@PathVariable String username) {
        UserLoginResponse response = useSer.getUserByUsername(username);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    ResponseEntity<UserLoginResponse> getUserById(@PathVariable Long id) {
        UserLoginResponse response = useSer.getUserById(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    ResponseEntity<UserLoginResponse> getUserById(@PathVariable Long id, @RequestBody UserLoginRequest request) {
        UserLoginResponse response = useSer.updateUser(id, request);
        return ResponseEntity.ok(response);
    }
}
