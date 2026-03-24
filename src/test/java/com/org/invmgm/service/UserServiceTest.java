package com.org.invmgm.service;

import com.org.invmgm.dto.UserLoginRequest;
import com.org.invmgm.dto.UserLoginResponse;
import com.org.invmgm.model.SecurityGroup;
import com.org.invmgm.model.UserLogin;
import com.org.invmgm.model.UserSecurityGroup;
import com.org.invmgm.repository.SecurityGroupRepository;
import com.org.invmgm.repository.UserLoginRepository;
import com.org.invmgm.repository.UserSecurityGroupRepository;
import com.org.invmgm.service.impl.UserLoginServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserLoginRepository userRepo;

    @Mock
    private SecurityGroupRepository grpRepo;

    @Mock
    private UserSecurityGroupRepository usrScuRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserLoginServiceImpl userService;

    @Test
    void createUser_shouldReturnSavedUser() {
        // Arrange
        UserLoginRequest request = new UserLoginRequest();
        request.setUsername("John");
        request.setSecurityGroupId("ADMIN");
        request.setEmailAddress("test@gmail.com");
        request.setPassword("123");

        UserLogin savedUser = new UserLogin();
        savedUser.setUsername("John");
        savedUser.setEmail("test@gmail.com");

        SecurityGroup group = new SecurityGroup();
        group.setId("ADMIN");

        // Mock behavior
        when(userRepo.findByUsernameEquals("John"))
                .thenReturn(Optional.empty());

        when(grpRepo.findById("ADMIN"))
                .thenReturn(Optional.of(group));

        when(passwordEncoder.encode("123"))
                .thenReturn("hashed_password");

        when(userRepo.save(any(UserLogin.class)))
                .thenReturn(savedUser);

        when(usrScuRepo.save(any(UserSecurityGroup.class)))
                .thenReturn(new UserSecurityGroup());

        // Act
        UserLoginResponse result = userService.registerUser(request);

        // Assert
        assertEquals("John", result.getUsername());

        // Verify interactions
        verify(userRepo).save(any(UserLogin.class));
        verify(grpRepo).findById("ADMIN");
        verify(usrScuRepo).save(any(UserSecurityGroup.class));
    }
}
