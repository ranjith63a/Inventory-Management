package com.org.invmgm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {

    private Long id;
    private String productCode;
    private String productName;
    private String productGroupName;
    private Character isControlSubstance;
    private ProductFeatureResponse productFeature;
}
