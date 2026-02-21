package com.org.invmgm.model;

import com.org.invmgm.common.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "enumeration")
public class Enumeration extends BaseEntity {

    @Id
    @Column(name = "enum_id", length = 20)
    private String id;

    private String enumCode;
    private String description;
    private int seqNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enum_type_id", nullable = false)
    private EnumerationType enumType;

    public Enumeration(String id, String enumCode, String description, int seqNum, EnumerationType enumType) {
        this.id = id;
        this.enumCode = enumCode;
        this.description = description;
        this.seqNum = seqNum;
        this.enumType = enumType;
    }

    public Enumeration () {}

    public Enumeration(String id, String enumCode, String description, int seqNum, String enumTypeId) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnumCode() {
        return enumCode;
    }

    public void setEnumCode(String enumCode) {
        this.enumCode = enumCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EnumerationType getEnumType() {
        return enumType;
    }

    public void setEnumType(EnumerationType enumType) {
        this.enumType = enumType;
    }

    public int getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(int seqNum) {
        this.seqNum = seqNum;
    }
}
