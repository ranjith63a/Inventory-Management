package com.org.invmgm.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private String productCode;
    private String productName;
    private String productGroupName;
    private Character isControlSubstance;
    @NotNull
    private Long featureId;
}
