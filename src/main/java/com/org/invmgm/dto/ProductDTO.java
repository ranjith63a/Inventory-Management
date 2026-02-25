package com.org.invmgm.dto;

import com.org.invmgm.model.ProductFeatureApplied;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    private Long id;
    private String productCode;
    private String productName;
    private String productGroupName;
    private Character isControlSubstance;
    @NotNull
    private Long featureId;
}
