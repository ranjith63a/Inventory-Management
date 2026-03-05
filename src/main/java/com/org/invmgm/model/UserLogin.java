package com.org.invmgm.model;

import com.org.invmgm.common.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "user_login")
public class UserLogin extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userLogin_seq")
    @SequenceGenerator(name = "userLogin_seq", sequenceName = "userLogin_sequence", initialValue = 10000, allocationSize = 1)
    @Column(name = "user_login_id")
    private Long id;

    @Column(unique = true)
    private String username;

    @Email
    private String email;
    private String password;
    private boolean enabled = true;


}
