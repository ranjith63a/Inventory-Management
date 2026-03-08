package com.org.invmgm.service.impl;

import com.org.invmgm.dto.UserLoginRequest;
import com.org.invmgm.dto.UserLoginResponse;
import com.org.invmgm.exception.DataNotFoundException;
import com.org.invmgm.model.SecurityGroup;
import com.org.invmgm.model.UserLogin;
import com.org.invmgm.model.UserSecurityGroup;
import com.org.invmgm.repository.SecurityGroupRepository;
import com.org.invmgm.repository.UserLoginRepository;
import com.org.invmgm.repository.UserSecurityGroupRepository;
import com.org.invmgm.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private final UserLoginRepository userRepo;

    @Autowired
    private final SecurityGroupRepository grpRepo;

    @Autowired
    private final UserSecurityGroupRepository usrScuRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserLoginResponse registerUser(UserLoginRequest request) {
        Optional<UserLogin> existingUser = userRepo.findByUsernameEquals(request.getUsername());

        if (existingUser.isPresent()) {
            throw new DataNotFoundException("User Name Already exists : " + request.getUsername());
        }
        SecurityGroup group = grpRepo.findById(request.getSecurityGroupId()).orElseThrow(() -> new DataNotFoundException("Security group does not exists : " + request.getSecurityGroupId()));

        UserLogin user = new UserLogin();
        user.setUsername(request.getUsername());

        String hashedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(hashedPassword);

        user.setEmail(request.getEmailAddress());
        UserLogin result = userRepo.save(user);

        UserSecurityGroup userGroup = new UserSecurityGroup();
        userGroup.setGroup(group);
        userGroup.setUser(result);
        usrScuRepo.save(userGroup);
        return responseMap(result);
    }

    private UserLoginResponse responseMap(UserLogin userLogin) {
        UserLoginResponse user = new UserLoginResponse();
        user.setUsername(userLogin.getUsername());
        return user;
    }
}
