package com.org.invmgm.service;

import com.org.invmgm.dto.UserLoginRequest;
import com.org.invmgm.dto.UserLoginResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserLoginService {

    UserLoginResponse registerUser(UserLoginRequest request);
    UserLoginResponse getUserByUsername(String username);
    UserLoginResponse getUserById(Long id);
    UserLoginResponse updateUser(Long id, UserLoginRequest request);
    void deleteUser(Long id);
    Page<UserLoginResponse> findAllUser(Pageable pageable);
}
