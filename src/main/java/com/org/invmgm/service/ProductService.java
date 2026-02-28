package com.org.invmgm.service;

import com.org.invmgm.dto.ProductRequest;
import com.org.invmgm.dto.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);
    ProductResponse updateProduct(Long id, ProductRequest request);
    ProductResponse getProduct(Long id);
    Page<ProductResponse> findAllProduct(Long id, String productName, Pageable pageable);
}
