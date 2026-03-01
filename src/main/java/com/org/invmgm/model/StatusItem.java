package com.org.invmgm.model;

import com.org.invmgm.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "status_item")
@Getter
@Setter
public class StatusItem extends BaseEntity {

    @Id
    @Column(name = "status_item_id", length = 20)
    private String id;

    @ManyToOne
    @JoinColumn(name = "status_item_type_id", nullable = false)
    private StatusItemType type;

    private String description;
    private int seqNum;

}
