package com.org.invmgm.service;

import com.org.invmgm.dto.ProductRequest;
import com.org.invmgm.dto.ProductResponse;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);
    ProductResponse updateProduct(Long id, ProductRequest request);
}
