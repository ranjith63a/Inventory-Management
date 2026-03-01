package com.org.invmgm.service;

import com.org.invmgm.dto.InventoryItemCreateRequest;
import com.org.invmgm.dto.InventoryItemResponse;
import com.org.invmgm.dto.InventoryItemUpdateRequest;

public interface InventoryItemService {
    InventoryItemResponse receiveInventoryItem (InventoryItemCreateRequest request);
    InventoryItemResponse updateInventoryItem (Long id, InventoryItemUpdateRequest request);
}
