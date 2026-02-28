package com.org.invmgm.repository;

import com.org.invmgm.model.ProductFeatureApplied;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductFeatureAppliedRepository extends JpaRepository<ProductFeatureApplied, Long> {
    ProductFeatureApplied findActiveByProductId(Long id);
    ProductFeatureApplied findByProductIdAndThruDateAfterOrThruDateIsNull(Long productId, LocalDateTime now);

    @Query("""
           SELECT p
           FROM ProductFeatureApplied p
           WHERE p.product.id = :productId
           AND (p.thruDate > CURRENT_TIMESTAMP OR p.thruDate IS NULL)
           """)
    List<ProductFeatureApplied> findActiveByProductIds(@Param("productId") Long productId);

    @Query("""
           SELECT pf
           FROM ProductFeatureApplied pf
           WHERE pf.productFeature.id = :productFeatureId
           """)
    List<ProductFeatureApplied> findByProductFeatureIds(@Param("productFeatureId") Long productFeatureId);
}
