package com.org.invmgm.model;

import com.org.invmgm.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "security_group")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SecurityGroup extends BaseEntity {

    @Id
    @Column(name = "group_id")
    private String id;

    private String groupName;
}
