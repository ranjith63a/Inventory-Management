package com.org.invmgm.repository;

import com.org.invmgm.model.ProductFeatureApplied;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductFeatureAppliedRepository extends JpaRepository<ProductFeatureApplied, Long> {
    ProductFeatureApplied findActiveByProductId(Long id);
}
