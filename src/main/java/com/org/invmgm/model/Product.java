package com.org.invmgm.model;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_sequence", initialValue = 10000, allocationSize = 1)
    @Column(name = "productId")
    private Long id;
    private String productCode;
    private String productName;
    private String productGroupName;
    private Character isControlSubstance;

    public Product(long id, String productCode, String productName, String productGroupName, Character isControlSubstance) {
        this.id = id;
        this.productCode = productCode;
        this.productName = productName;
        this.productGroupName = productGroupName;
        this.isControlSubstance = isControlSubstance;
    }

    public long getId() {
        return id;
    }

    public String getProductGroupName() {
        return productGroupName;
    }

    public void setProductGroupName(String productGroupName) {
        this.productGroupName = productGroupName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Character getIsControllSubstance() {
        return isControlSubstance;
    }

    public void setIsControllSubstance(Character isControllSubstance) {
        this.isControlSubstance = isControllSubstance;
    }
}
