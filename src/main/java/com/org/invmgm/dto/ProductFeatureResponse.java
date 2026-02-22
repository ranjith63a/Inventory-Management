package com.org.invmgm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFeatureResponse {

    private Long id;
    private String featureCode;
    private String featureValue;
}
