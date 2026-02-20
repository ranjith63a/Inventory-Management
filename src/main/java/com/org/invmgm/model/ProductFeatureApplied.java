package com.org.invmgm.model;

import com.org.invmgm.model.key.ProductFeatureAppliedKey;
import jakarta.persistence.*;

@Entity
public class ProductFeatureApplied {

    @EmbeddedId
    private ProductFeatureAppliedKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id")   // PK column
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productFeatureId")
    @JoinColumn(name = "product_feature_id")  // PK column
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
