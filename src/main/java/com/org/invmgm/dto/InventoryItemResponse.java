package com.org.invmgm.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class InventoryItemResponse {

    private Long inventoryItemId;
    private FacilityResponse facility;
    private ProductResponse product;
    private String inventoryItemTypeId;
    private BigDecimal quantity;
    private String transactionUomId;
    private String vendorId;
    private String comments;
    private String lotId;

    public InventoryItemResponse() {
    }
}
