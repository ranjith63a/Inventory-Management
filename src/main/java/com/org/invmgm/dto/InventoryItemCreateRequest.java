package com.org.invmgm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class InventoryItemCreateRequest {

    @NotNull
    private Long facilityId;
    @NotNull
    private Long productId;
    @NotNull
    @Positive
    private BigDecimal quantity;
    @NotBlank
    private String transactionUomId;
    @NotBlank
    private String inventoryItemTypeId;
    @NotBlank
    private String statusId;
    private String lotId;
    private String comments;
    private String vendorId;
}
