package com.org.invmgm.service.impl;

import com.org.invmgm.dto.ProductRequest;
import com.org.invmgm.dto.ProductResponse;
import com.org.invmgm.dto.ProductFeatureResponse;
import com.org.invmgm.exception.DataNotFoundException;
import com.org.invmgm.model.Product;
import com.org.invmgm.model.ProductFeature;
import com.org.invmgm.model.ProductFeatureApplied;
import com.org.invmgm.repository.ProductFeatureAppliedRepository;
import com.org.invmgm.repository.ProductFeatureRepository;
import com.org.invmgm.repository.ProductRepository;
import com.org.invmgm.service.ProductFeatureService;
import com.org.invmgm.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Validated
public class ProductServiceImpl implements ProductService {

    private final ProductRepository proRepo;
    private final ProductFeatureRepository proFeaRepo;
    private final ProductFeatureAppliedRepository proFeaAppRepo;
    private final ProductFeatureService productFeatureService;

    public ProductServiceImpl(ProductRepository proRepo, ProductFeatureRepository proFeaRepo, ProductFeatureAppliedRepository proFeaAppRepo, ProductFeatureService productFeatureService) {
        this.proRepo = proRepo;
        this.proFeaRepo = proFeaRepo;
        this.proFeaAppRepo = proFeaAppRepo;
        this.productFeatureService = productFeatureService;
    }

    @Transactional
    @Override
    public ProductResponse createProduct(ProductRequest request) {
        // Now we don't validate here, because we use @Valid at the controller
        /*// Validate product feature id exists
        if (request.getFeatureId() == null) {
            throw new DataNotFoundException("Product Feature Id cannot be empty");
        }*/

        ProductFeature feature = proFeaRepo.findById(request.getFeatureId())
                .orElseThrow(() -> new DataNotFoundException("Product Feature not found with id:" + request.getFeatureId()));

        Product pro = new Product();
        pro.setProductName(request.getProductName());
        pro.setProductCode(request.getProductCode());
        pro.setProductGroupName(request.getProductGroupName());
        pro.setIsControlSubstance(request.getIsControlSubstance());
        Product response = proRepo.save(pro);

        ProductFeatureApplied applied = new ProductFeatureApplied(response, feature, LocalDateTime.now());
        proFeaAppRepo.save(applied);

        return responseMap(response);
    }

    @Transactional
    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        // Now we don't validate here, because we use @Valid at the controller
        /*// Validate product feature id exists
        if (request.getFeatureId() == null) {
            throw new DataNotFoundException("Product Feature Id cannot be empty");
        }*/

        Product pro = proRepo.findById(id).orElseThrow(() -> new DataNotFoundException("Could not find the Product Id: " + id));

        if (request.getProductName() != null) {
            pro.setProductName(request.getProductName());
        }

        if (request.getProductCode() != null) {
            pro.setProductCode(request.getProductCode());
        }

        if (request.getIsControlSubstance() != null) {
            pro.setIsControlSubstance(request.getIsControlSubstance());
        }

        if (request.getProductGroupName() != null) {
            pro.setProductGroupName(request.getProductGroupName());
        }

        // no need to save because hibernate manage to save
        //Product response = proRepo.save(pro);

        if (request.getFeatureId() != null) {

            // close existing feature
            List<ProductFeatureApplied> existing = proFeaAppRepo.findActiveByProductIds(id);

            if (existing != null) {
                existing.get(0).setThruDate(LocalDateTime.now());
            }

            assert existing != null;
            proFeaAppRepo.save(existing.get(0));

            // Validate product feature id exists
            ProductFeature feature = proFeaRepo.findById(request.getFeatureId())
                    .orElseThrow(() -> new DataNotFoundException("Product Feature not found with id:" + request.getFeatureId()));

            ProductFeatureApplied applied = new ProductFeatureApplied(pro, feature, LocalDateTime.now());

            proFeaAppRepo.save(applied);
        }
        return responseMap(pro);
    }

    @Override
    public ProductResponse getProduct(Long id) {
        Product product = proRepo.findById(id).orElseThrow(() -> new DataNotFoundException("Could Not fine the Product Id: " + id));
        return responseMap(product);
    }

    private ProductResponse responseMap(Product response) {
        ProductResponse product = new ProductResponse();
        product.setProductCode(response.getProductCode());
        product.setProductName(response.getProductName());
        product.setProductGroupName(response.getProductGroupName());
        product.setIsControlSubstance(response.getIsControlSubstance());
        product.setId(response.getId());

        // Fetch feature dynamically (if needed)
        List<ProductFeatureApplied> applied =  proFeaAppRepo.findActiveByProductIds(response.getId());
        if (applied != null) {
            ProductFeatureResponse proFea = productFeatureService.getProductFeatureById(applied.get(0).getProductFeature().getId());
            product.setProductFeature(proFea);
        }
        return product;
    }

    @Override
    public Page<ProductResponse> findAllProduct(Long id, String productName, Pageable pageable) {
        Page<Product> response;
        if (id != null && productName != null) {
            response = proRepo.findByIdEqualsAndProductNameContainingIgnoreCase(id, productName, pageable);
        } else if (id != null) {
            response = proRepo.findById(id, pageable);
        } else if (productName != null) {
            response = proRepo.findByProductNameContainingIgnoreCase(productName, pageable);
        } else {
            response = proRepo.findAll(pageable);
        }
        return response.map(this::responseMap);
    }
}
