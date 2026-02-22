package com.org.invmgm.service.impl;


import com.org.invmgm.dto.ProductFeatureRequest;
import com.org.invmgm.dto.ProductFeatureResponse;
import com.org.invmgm.model.ProductFeature;
import com.org.invmgm.repository.ProductFeatureRepository;
import com.org.invmgm.service.ProductFeatureService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductFeatureImpl implements ProductFeatureService {

    private final ProductFeatureRepository proFeaRepo;

    public ProductFeatureImpl(ProductFeatureRepository proFeaRepo) {
        this.proFeaRepo = proFeaRepo;
    }

    @Override
    public ProductFeatureResponse create(ProductFeatureRequest request) {

        ProductFeature newEntity = new ProductFeature();
        newEntity.setProductFeatureCode(request.getFeatureCode());
        newEntity.setProductFeatureValue(request.getFeatureValue());

        ProductFeature result = proFeaRepo.save(newEntity);
        return responseMap(result);
    }

    @Override
    public ProductFeatureResponse getById(Long id) {
        ProductFeature productFeature = proFeaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Feature not found with id: " + id));

        return responseMap(productFeature);
    }

    private ProductFeatureResponse responseMap(ProductFeature productFeature) {

        ProductFeatureResponse response = new ProductFeatureResponse();
        response.setId(productFeature.getId());
        response.setFeatureCode(productFeature.getProductFeatureCode());
        response.setFeatureValue(productFeature.getProductFeatureValue());

        return response;
    }

    @Override
    public ProductFeatureResponse update(Long id, ProductFeatureRequest request) {
        ProductFeature productFeature = proFeaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Feature not found with id: " + id));

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
    public void delete(Long id) {

        ProductFeature productFeature = proFeaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Feature not found with id: " + id));

        proFeaRepo.delete(productFeature);
    }
}
