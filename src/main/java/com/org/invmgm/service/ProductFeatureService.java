package com.org.invmgm.service;

import com.org.invmgm.dto.ProductFeatureRequest;
import com.org.invmgm.dto.ProductFeatureResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductFeatureService {

    ProductFeatureResponse createProductFeature (ProductFeatureRequest request);

    ProductFeatureResponse getProductFeatureById(Long id);

    Page<ProductFeatureResponse> findAllProductFeature(String productFeatureCode, Pageable pageable);

    ProductFeatureResponse updateProductFeature(Long id, ProductFeatureRequest request);

    void deleteProductFeature(Long id);
}
