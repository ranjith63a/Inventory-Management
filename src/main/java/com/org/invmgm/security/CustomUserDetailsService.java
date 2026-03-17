package com.org.invmgm.security;

import com.org.invmgm.exception.DataNotFoundException;
import com.org.invmgm.model.UserLogin;
import com.org.invmgm.model.UserSecurityGroup;
import com.org.invmgm.repository.UserLoginRepository;
import com.org.invmgm.repository.UserSecurityGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserLoginRepository userRepository;
    @Autowired
    private UserSecurityGroupRepository securityGroupRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserLogin user = userRepository.findByUsernameEquals(username)
                .orElseThrow(() ->
                        new DataNotFoundException("User not found"));

        List<UserSecurityGroup> securityGroupList = securityGroupRepository.findActiveGroupsByUserId(user.getId());

        List<SimpleGrantedAuthority> authorities = securityGroupList.stream()
                        .map(usg -> new SimpleGrantedAuthority(
                                "ROLE_" + usg.getGroup().getId()
                        ))
                        .toList();
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}