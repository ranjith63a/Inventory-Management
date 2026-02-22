package com.org.invmgm.service;

import com.org.invmgm.dto.ProductFeatureRequest;
import com.org.invmgm.dto.ProductFeatureResponse;


public interface ProductFeatureService {

    ProductFeatureResponse create (ProductFeatureRequest request);

    ProductFeatureResponse getById (Long id);

    ProductFeatureResponse update (Long id, ProductFeatureRequest request);

    void delete(Long id);
}
