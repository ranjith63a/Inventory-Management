package com.org.invmgm.controller;

import com.org.invmgm.dto.InventoryItemCreateRequest;
import com.org.invmgm.dto.InventoryItemResponse;
import com.org.invmgm.dto.InventoryItemUpdateRequest;
import com.org.invmgm.dto.InventoryTransferRequest;
import com.org.invmgm.service.impl.InventoryItemServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryItemController {

    private final InventoryItemServiceImpl service;

    public InventoryItemController(InventoryItemServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/receiveInventory")
    public ResponseEntity<InventoryItemResponse> receivedInventoryItem(@Valid @RequestBody InventoryItemCreateRequest request) {
        InventoryItemResponse response = service.receiveInventoryItem(request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<InventoryItemResponse> receivedInventoryItem(@PathVariable Long id, @Valid @RequestBody InventoryItemUpdateRequest request) {
        InventoryItemResponse response = service.updateInventoryItem(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<InventoryItemResponse>> findAllInventory(Pageable pageable) {
        Page<InventoryItemResponse> response = service.findAllInventory(pageable);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/transferInventory/{inventoryItemId}")
    public ResponseEntity<InventoryItemResponse> transferInventory(
            @PathVariable Long inventoryItemId,
            @RequestBody InventoryTransferRequest request) {

        InventoryItemResponse response = service.transferInventoryItem(
                inventoryItemId,
                request.getTransferQuantity(),
                request.getTransactionUomId(),
                request.getTransferReasonId(),
                request.getFacilityId(),
                request.getComments()
        );

        return ResponseEntity.ok(response);
    }
}
