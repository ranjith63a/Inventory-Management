package com.org.invmgm.model;

import com.org.invmgm.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "status_item_type")
@Getter
@Setter
public class StatusItemType extends BaseEntity {

    @Id
    @Column(name = "status_item_type_id", length = 20)
    private String id;
    private String description;

    public StatusItemType(String id, String description) {
        this.id = id;
        this.description = description;
    }
    public StatusItemType() {}
}
