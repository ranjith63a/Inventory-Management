package com.org.invmgm.service;

import com.org.invmgm.dto.InventoryItemCreateRequest;
import com.org.invmgm.dto.InventoryItemResponse;
import com.org.invmgm.dto.InventoryItemUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InventoryItemService {
    InventoryItemResponse receiveInventoryItem (InventoryItemCreateRequest request);
    InventoryItemResponse updateInventoryItem (Long id, InventoryItemUpdateRequest request);
    Page<InventoryItemResponse> findAllInventory (Pageable pageable);
}
