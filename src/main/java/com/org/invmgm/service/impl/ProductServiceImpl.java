package com.org.invmgm.service.impl;

import com.org.invmgm.dto.ProductDTO;
import com.org.invmgm.exception.DataNotFoundException;
import com.org.invmgm.model.Product;
import com.org.invmgm.model.ProductFeature;
import com.org.invmgm.model.ProductFeatureApplied;
import com.org.invmgm.repository.ProductFeatureAppliedRepository;
import com.org.invmgm.repository.ProductFeatureRepository;
import com.org.invmgm.repository.ProductRepository;
import com.org.invmgm.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository proRepo;
    private final ProductFeatureRepository proFeaRepo;
    private final ProductFeatureAppliedRepository proFeaAppRepo;

    public ProductServiceImpl(ProductRepository proRepo, ProductFeatureRepository proFeaRepo, ProductFeatureAppliedRepository proFeaAppRepo) {
        this.proRepo = proRepo;
        this.proFeaRepo = proFeaRepo;
        this.proFeaAppRepo = proFeaAppRepo;
    }

    @Transactional
    @Override
    public ProductDTO createProduct(ProductDTO request) {
        Product pro = new Product();
        pro.setProductName(request.getProductName());
        pro.setProductCode(request.getProductCode());
        pro.setProductGroupName(request.getProductGroupName());
        pro.setIsControllSubstance(request.getIsControlSubstance());
        Product response = proRepo.save(pro);

        if (request.getFeatureId() != null) {
            ProductFeature proFea = proFeaRepo.getReferenceById(request.getFeatureId());

            ProductFeatureApplied applied = new ProductFeatureApplied(response, proFea);
            proFeaAppRepo.save(applied);
        }

        return responseMap(response);
    }

    @Transactional
    @Override
    public ProductDTO updateProduct(Long id, ProductDTO request) {
        Product pro = proRepo.findById(id).orElseThrow(() -> new DataNotFoundException("Could not find the Product Id: " + id));

        if (request.getProductName() != null) {
            pro.setProductName(request.getProductName());
        }

        if (request.getProductCode() != null) {
            pro.setProductCode(request.getProductCode());
        }

        if (request.getIsControlSubstance() != null) {
            pro.setIsControllSubstance(request.getIsControlSubstance());
        }

        if (request.getProductGroupName() != null) {
            pro.setProductGroupName(request.getProductGroupName());
        }

        // no need to save because hibernate manage to save
        //Product response = proRepo.save(pro);

        if (request.getFeatureId() != null) {

            // close existing feature
            ProductFeatureApplied existing = proFeaAppRepo.findActiveByProductId(id);

            if (existing != null) {
                existing.setThruDate(LocalDateTime.now());
            }

            ProductFeature feature = proFeaRepo.getReferenceById(request.getFeatureId());

            ProductFeatureApplied applied = new ProductFeatureApplied(pro, feature);

            proFeaAppRepo.save(applied);
        }
        return responseMap(pro);
    }

    private ProductDTO responseMap(Product response) {
        ProductDTO product = new ProductDTO();
        product.setProductCode(response.getProductCode());
        product.setProductName(response.getProductName());
        product.setProductGroupName(response.getProductGroupName());
        product.setIsControlSubstance(response.getIsControllSubstance());
        product.setId(response.getId());
        return product;
    }
}
