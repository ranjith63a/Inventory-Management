package com.org.invmgm.model;

import com.org.invmgm.common.entity.BaseEntity;
import com.org.invmgm.model.key.ProductFeatureAppliedKey;
import jakarta.persistence.*;

@Entity
public class ProductFeatureApplied extends BaseEntity {

    @EmbeddedId
    private ProductFeatureAppliedKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id", nullable = false)   // PK column
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productFeatureId")
    @JoinColumn(name = "product_feature_id", nullable = false)  // PK column
    private ProductFeature productFeature;

    public ProductFeatureApplied() {}

    public ProductFeatureApplied(Product product, ProductFeature productFeature) {
        this.product = product;
        this.productFeature = productFeature;
        this.id = new ProductFeatureAppliedKey(
                product.getId(),
                productFeature.getId()
        );
    }
}
