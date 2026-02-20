package com.org.invmgm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Uom {

    @Id
    @Column(name = "uomId", length = 20)
    private String id;
    private BigDecimal conversion;
    private String description;

    public Uom () {}

    public Uom(String id, BigDecimal conversion, String description) {
        this.id = id;
        this.conversion = conversion;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getConversion() {
        return conversion;
    }

    public void setConversion(BigDecimal conversion) {
        this.conversion = conversion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
