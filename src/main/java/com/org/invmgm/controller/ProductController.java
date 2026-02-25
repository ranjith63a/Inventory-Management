package com.org.invmgm.controller;

import com.org.invmgm.dto.ProductDTO;
import com.org.invmgm.service.ProductService;
import com.org.invmgm.service.impl.ProductServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductServiceImpl proSer;

    public ProductController(ProductServiceImpl proSer) {
        this.proSer = proSer;
    }

    @PostMapping
    public ProductDTO create(ProductDTO request) {
        ProductDTO productDTO = proSer.createProduct(request);
        return productDTO;
    }
}
