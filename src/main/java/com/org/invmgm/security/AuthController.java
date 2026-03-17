package com.org.invmgm.security;

import com.org.invmgm.dto.AuthResponse;
import com.org.invmgm.exception.DataNotFoundException;
import com.org.invmgm.model.UserLogin;
import com.org.invmgm.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserLoginRepository userRepo;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserLogin user = userRepo.findByUsernameEquals(request.getUsername())
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        String token = jwtService.generateToken(request.getUsername());
        long expiry = jwtService.getExpirationTime();



        return AuthResponse.builder()
                .status(201)
                .message("Login successful")
                .accessToken(token)
                .tokenType("Bearer")
                .expiresIn(expiry)
                .username(user.getUsername())
                .build();
    }
}
