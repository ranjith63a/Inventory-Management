package com.org.invmgm.service;

import com.org.invmgm.dto.InventoryItemCreateRequest;
import com.org.invmgm.dto.InventoryItemResponse;
import com.org.invmgm.dto.InventoryItemUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface InventoryItemService {
    InventoryItemResponse receiveInventoryItem (InventoryItemCreateRequest request);
    InventoryItemResponse updateInventoryItem (Long id, InventoryItemUpdateRequest request);
    Page<InventoryItemResponse> findAllInventory (Pageable pageable);
    InventoryItemResponse transferInventoryItem(Long inventoryItemId, BigDecimal transferQuantity, String transactionUomId, String transferReasonId,
                                                Long facilityId, String comments);
}
