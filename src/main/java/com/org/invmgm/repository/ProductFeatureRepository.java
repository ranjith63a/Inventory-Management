package com.org.invmgm.repository;

import com.org.invmgm.dto.ProductFeatureResponse;
import com.org.invmgm.model.ProductFeature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductFeatureRepository extends JpaRepository<ProductFeature, Long> {
    Page<ProductFeature> findByProductFeatureCodeContainingIgnoreCase(String productFeatureCode, Pageable pageable);
}
