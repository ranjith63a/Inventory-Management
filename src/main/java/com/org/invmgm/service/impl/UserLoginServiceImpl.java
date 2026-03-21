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
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "users") // ✅ sets default cache name for all methods in this class
public class UserLoginServiceImpl implements UserLoginService {

    private final UserLoginRepository userRepo;
    private final SecurityGroupRepository grpRepo;
    private final UserSecurityGroupRepository usrScuRepo;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    @CacheEvict(
            cacheNames = "users",
            key = "'all'"           // evict the user-list cache after a new user is created
    )
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

    @Override
    @Cacheable(
            cacheNames = "users",
            key = "#id",
            unless = "#result == null"   // never cache a null response
    )
    public UserLoginResponse getUserById(Long id) {
        log.debug("Cache MISS — loading user {} from DB", id);
        return userRepo.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new DataNotFoundException("User not found: " + id));
    }

    private UserLoginResponse responseMap(UserLogin userLogin) {
        UserLoginResponse user = new UserLoginResponse();
        user.setId(userLogin.getId());
        user.setUsername(userLogin.getUsername());
        user.setEmail(userLogin.getEmail());
        user.setCreatedAt(userLogin.getCreatedTimestamp());
        return user;
    }

    @Cacheable(
            cacheNames = "users",
            key = "'username:' + #username",   //  prefix avoids key collision with numeric IDs
            unless = "#result == null"
    )
    public UserLoginResponse getUserByUsername(String username) {
        log.debug("Cache MISS — loading user '{}' from DB", username);
        return userRepo.findByUsernameEquals(username)
                .map(this::toResponse)
                .orElseThrow(() -> new DataNotFoundException("User not found: " + username));
    }

    // ─────────────────────────────────────────────────────────
    //  UPDATE — refresh cache entry, evict list
    // ─────────────────────────────────────────────────────────

    @Transactional
    @Caching(
            put = {
                    @CachePut(cacheNames = "users", key = "#id")             //  refresh ID-based entry
            },
            evict = {
                    @CacheEvict(cacheNames = "users", key = "'username:' + #result.username"), //  evict old username key
                    @CacheEvict(cacheNames = "users", key = "'all'")          //  evict list cache
            }
    )
    public UserLoginResponse updateUser(Long id, UserLoginRequest request) {
        log.debug("Updating user id={}", id);
        UserLogin existing = userRepo.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User not found: " + id));

        existing.setEmail(request.getEmailAddress());
        // Note: username and password changes need extra care in real systems
        UserLogin saved = userRepo.save(existing);

        log.info("User updated: id={}", saved.getId());
        return toResponse(saved);
    }

    // ─────────────────────────────────────────────────────────
    //  DELETE — evict all related cache entries
    // ─────────────────────────────────────────────────────────

    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = "users", key = "#id"),                          //  evict by ID
            @CacheEvict(cacheNames = "users", key = "'username:' + #username"),      //  evict by username
            @CacheEvict(cacheNames = "users", key = "'all'")                         //  evict list
    })
    public void deleteUser(Long id, String username) {
        log.debug("Deleting user id={}", id);
        userRepo.deleteById(id);
        log.info("User deleted and cache evicted: id={}", id);
    }

    // ─────────────────────────────────────────────────────────
    //  MAPPER
    // ─────────────────────────────────────────────────────────

    private UserLoginResponse toResponse(UserLogin user) {
        return UserLoginResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdAt(user.getCreatedTimestamp())
                .build();
    }
}
