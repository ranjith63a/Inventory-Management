package com.org.invmgm.service.impl;


import com.org.invmgm.dto.ProductFeatureRequest;
import com.org.invmgm.dto.ProductFeatureResponse;
import com.org.invmgm.exception.DataNotFoundException;
import com.org.invmgm.model.ProductFeature;
import com.org.invmgm.repository.ProductFeatureRepository;
import com.org.invmgm.service.ProductFeatureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class ProductFeatureServiceImpl implements ProductFeatureService {

    private final ProductFeatureRepository proFeaRepo;

    public ProductFeatureServiceImpl(ProductFeatureRepository proFeaRepo) {
        this.proFeaRepo = proFeaRepo;
    }

    @Override
    public ProductFeatureResponse createProductFeature(ProductFeatureRequest request) {

        ProductFeature newEntity = new ProductFeature();
        newEntity.setProductFeatureCode(request.getFeatureCode());
        newEntity.setProductFeatureValue(request.getFeatureValue());
        newEntity.setFromDate(LocalDateTime.now());

        ProductFeature result = proFeaRepo.save(newEntity);
        return responseMap(result);
    }

    @Override
    public ProductFeatureResponse getProductFeatureById(Long id) {
        ProductFeature productFeature = proFeaRepo.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Product Feature not found with id: " + id));

        return responseMap(productFeature);
    }

    @Override
    public Page<ProductFeatureResponse> findAllProductFeature(String productFeatureCode, Pageable pageable) {
        Page<ProductFeature> page;
        if (productFeatureCode != null && !productFeatureCode.isBlank()) {
            page = proFeaRepo.findByProductFeatureCodeContainingIgnoreCase(productFeatureCode, pageable);
        } else {
            page = proFeaRepo.findAll(pageable);
        }
        return page.map(this::responseMap);
    }

    private ProductFeatureResponse responseMap(ProductFeature productFeature) {

        ProductFeatureResponse response = new ProductFeatureResponse();
        response.setId(productFeature.getId());
        response.setFeatureCode(productFeature.getProductFeatureCode());
        response.setFeatureValue(productFeature.getProductFeatureValue());

        return response;
    }

    @Override
    public ProductFeatureResponse updateProductFeature(Long id, ProductFeatureRequest request) {
        ProductFeature productFeature = proFeaRepo.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Product Feature not found with id: " + id));

        if (request.getFeatureCode() != null) {
            productFeature.setProductFeatureCode(request.getFeatureCode());
        }
        if (request.getFeatureValue() != null) {
            productFeature.setProductFeatureValue(request.getFeatureValue());
        }

        ProductFeature result = proFeaRepo.save(productFeature);
        return responseMap(result);
    }

    @Override
    public void deleteProductFeature(Long id) {

        ProductFeature productFeature = proFeaRepo.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Product Feature not found with id: " + id));

        proFeaRepo.delete(productFeature);
    }
}
