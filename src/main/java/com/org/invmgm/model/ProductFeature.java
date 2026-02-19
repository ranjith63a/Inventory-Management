package com.org.invmgm.model;

import jakarta.persistence.*;

@Entity
public class ProductFeature {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_fea_seq")
    @SequenceGenerator(name = "product_fea_seq", sequenceName = "product_fea_sequence", initialValue = 10000, allocationSize = 1)
    @Column(name = "productFeatureId")
    private Long id;
    private String productFeatureCode;
    private String productFeatureValue;


    public ProductFeature(Long id, String productFeatureCode, String productFeatureValue) {
        this.id = id;
        this.productFeatureCode = productFeatureCode;
        this.productFeatureValue = productFeatureValue;
    }

    public Long getId() {
        return id;
    }

    public String getProductFeatureValue() {
        return productFeatureValue;
    }

    public void setProductFeatureValue(String productFeatureValue) {
        this.productFeatureValue = productFeatureValue;
    }

    public String getProductFeatureCode() {
        return productFeatureCode;
    }

    public void setProductFeatureCode(String productFeatureCode) {
        this.productFeatureCode = productFeatureCode;
    }
}
