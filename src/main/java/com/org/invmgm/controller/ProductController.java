package com.org.invmgm.controller;

import com.org.invmgm.dto.ProductRequest;
import com.org.invmgm.dto.ProductResponse;
import com.org.invmgm.service.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductServiceImpl proSer;

    public ProductController(ProductServiceImpl proSer) {
        this.proSer = proSer;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
        ProductResponse productResponse = proSer.createProduct(request);
        return ResponseEntity.ok(productResponse);
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> fidAll(@Valid @RequestParam(required = false) Long id, @RequestParam(required = false) String productName, Pageable pageable) {
        Page<ProductResponse> productResponse = proSer.findAllProduct(id, productName, pageable);
        return ResponseEntity.ok(productResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> get(@PathVariable Long id) {
        ProductResponse productResponse = proSer.getProduct(id);
        return ResponseEntity.ok(productResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @RequestBody ProductRequest request) {
        ProductResponse productResponse = proSer.updateProduct(id, request);
        return ResponseEntity.ok(productResponse);
    }
}
