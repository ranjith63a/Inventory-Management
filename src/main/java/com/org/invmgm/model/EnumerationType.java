package com.org.invmgm.model;

import com.org.invmgm.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "enumeration_type")
public class EnumerationType extends BaseEntity {

    @Id
    @Column(name = "enum_type_id", length = 20)
    private String id;

    private String description;

    public EnumerationType () {}

    public EnumerationType(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
