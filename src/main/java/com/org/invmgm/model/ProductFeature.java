package com.org.invmgm.model;

import com.org.invmgm.common.entity.BaseEntity;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
public class ProductFeature extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_fea_seq")
    @SequenceGenerator(name = "product_fea_seq", sequenceName = "product_fea_sequence", initialValue = 10000, allocationSize = 1)
    @Column(name = "product_feature_id", nullable = false)
    private Long id;
    private String productFeatureCode;
    private String productFeatureValue;
    private LocalDateTime fromDate;
    private LocalDateTime thruDate;


    public ProductFeature(Long id, String productFeatureCode, String productFeatureValue, LocalDateTime fromDate, LocalDateTime thruDate) {
        this.id = id;
        this.productFeatureCode = productFeatureCode;
        this.productFeatureValue = productFeatureValue;
        this.fromDate = fromDate;
        this.thruDate = thruDate;
    }

    public ProductFeature(LocalDateTime fromDate, LocalDateTime thruDate) {
        this.fromDate = fromDate;
        this.thruDate = thruDate;
    }

    public ProductFeature() {}

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

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getThruDate() {
        return thruDate;
    }

    public void setThruDate(LocalDateTime thruDate) {
        this.thruDate = thruDate;
    }
}
