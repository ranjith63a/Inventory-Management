package com.org.invmgm.controller;

import com.org.invmgm.dto.ProductFeatureRequest;
import com.org.invmgm.dto.ProductFeatureResponse;
import com.org.invmgm.service.impl.ProductFeatureServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/productFeature")
public class ProductFeatureController {

    private final ProductFeatureServiceImpl service;

    public ProductFeatureController(ProductFeatureServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductFeatureResponse> createProductFeature(@Valid @RequestBody ProductFeatureRequest request) {
        ProductFeatureResponse response = service.createProductFeature(request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductFeatureResponse> updateProductFeature(@PathVariable Long id, @Valid @RequestBody ProductFeatureRequest request) {
        ProductFeatureResponse response = service.updateProductFeature(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<ProductFeatureResponse>> findAllProductFeature(
            @RequestParam(required = false) String productFeatureCode, Pageable pageable) {

        return ResponseEntity.ok(service.findAllProductFeature(productFeatureCode, pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductFeatureResponse> findProductFeature(@PathVariable Long id) {
        ProductFeatureResponse response = service.getProductFeatureById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public void deleteProductFeature(@PathVariable Long id) {
        service.deleteProductFeature(id);
    }
}
