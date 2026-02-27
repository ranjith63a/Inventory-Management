package com.org.invmgm.model.key;

import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class ProductFeatureAppliedKey implements Serializable {

    private Long productId;
    private Long productFeatureId;
    private LocalDateTime fromDate;

    public ProductFeatureAppliedKey(Long productId, Long productFeatureId, LocalDateTime fromDate) {
        this.productId = productId;
        this.productFeatureId = productFeatureId;
        this.fromDate = fromDate;
    }

    public ProductFeatureAppliedKey () {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductFeatureAppliedKey)) return false;
        ProductFeatureAppliedKey that = (ProductFeatureAppliedKey) o;
        return Objects.equals(productId, that.productId) &&
                Objects.equals(productFeatureId, that.productFeatureId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productFeatureId, fromDate);
    }

    @PrePersist
    protected void onCreateFromDate() {
        this.fromDate = LocalDateTime.now();
    }
}
