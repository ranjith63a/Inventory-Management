package com.org.invmgm.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class InventoryItemUpdateRequest {

    private String lotId;
    private String comments;
    private String vendorId;
}
