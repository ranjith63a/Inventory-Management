package com.org.invmgm.service;

import com.org.invmgm.dto.ProductFeatureRequest;
import com.org.invmgm.dto.ProductFeatureResponse;
import com.org.invmgm.model.ProductFeature;
import com.org.invmgm.repository.ProductFeatureRepository;
import com.org.invmgm.service.impl.ProductFeatureServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class ProductFeatureServiceTest {

    @InjectMocks
    private ProductFeatureServiceImpl proSer;

    @Mock
    private ProductFeatureRepository proFeaRepo;

    @Test
    void createProductFeature_shouldReturnSavedProductFeature() {
        // Arrange
        ProductFeatureRequest request = new ProductFeatureRequest();
        request.setFeatureCode("TEST_FEATURE_1");
        request.setFeatureValue("TEST_FEATURE_1");

        ProductFeature entity = new ProductFeature();
        entity.setProductFeatureValue("TEST_FEATURE_1");
        entity.setProductFeatureCode("TEST_FEATURE_1");

        // Mock Test
        when(proFeaRepo.save(any(ProductFeature.class)))
                .thenReturn(entity);

        // Act
        ProductFeatureResponse response = proSer.createProductFeature(request);

        //  log.info("response = == = = "  + response.getFeatureCode());
        // Assert
        assertEquals("TEST_FEATURE_1", response.getFeatureCode());

        // verify
        verify(proFeaRepo).save(any(ProductFeature.class));
    }
}
