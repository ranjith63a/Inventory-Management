package com.org.invmgm.service;

import com.org.invmgm.dto.UserLoginRequest;
import com.org.invmgm.dto.UserLoginResponse;

public interface UserLoginService {

    UserLoginResponse registerUser(UserLoginRequest request);
    UserLoginResponse getUserByUsername(String username);
    UserLoginResponse getUserById(Long id);
    UserLoginResponse updateUser(Long id, UserLoginRequest request);
}
