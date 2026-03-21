package com.org.invmgm.model;

import com.org.invmgm.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "user_login")
@Getter
@Setter
@NoArgsConstructor                    // ✅ required by JPA
@ToString(exclude = "password")       // ✅ never print password in logs
@EqualsAndHashCode(
        onlyExplicitlyIncluded = true,
        callSuper = false
)
public class UserLogin extends BaseEntity implements Serializable {  // ✅ required for Redis cache

    @Serial
    private static final long serialVersionUID = 1L;  // ✅ always declare explicitly

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_login_seq")
    @SequenceGenerator(
            name            = "user_login_seq",
            sequenceName    = "user_login_sequence",
            initialValue    = 10000,
            allocationSize  = 1
    )
    @Column(name = "user_login_id")
    private Long id;

    @EqualsAndHashCode.Include         // ✅ business key for equals/hashCode
    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)  // ✅ email should also be unique
    private String email;                     // ✅ @Email moved to DTO where it belongs

    @Column(nullable = false)
    private String password;                  // ✅ never expose in toString (excluded above)

    private boolean enabled = true;
}