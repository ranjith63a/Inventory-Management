package com.org.invmgm.service;

import com.org.invmgm.dto.ProductDTO;

public interface ProductService {
    ProductDTO createProduct(ProductDTO request);
    ProductDTO updateProduct(Long id, ProductDTO request);
}
