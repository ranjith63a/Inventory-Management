package com.org.invmgm.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFeatureRequest {

    // This is validated before service method call
    @NotBlank(message = "Product Code cannot be empty")
    private String featureCode;
    private String featureValue;
}
