package com.org.invmgm.model;

import com.org.invmgm.common.entity.BaseEntity;
import com.org.invmgm.model.key.UserSecurityGroupAppliedId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_security_group")
@Getter
@Setter
public class UserSecurityGroup extends BaseEntity {

    @EmbeddedId
    private UserSecurityGroupAppliedId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userLoginId")
    @JoinColumn(name = "user_login_id", nullable = false)   // PK column
    private UserLogin user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("groupId")
    @JoinColumn(name = "group_id", nullable = false)  // PK column
    private SecurityGroup group;

    private LocalDateTime thruDate;

    @PrePersist
    public void prePersist() {
        if (id.getFromDate() == null) {
            id.setFromDate(LocalDateTime.now());
        }
    }

}
