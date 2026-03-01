package com.org.invmgm.controller;

import com.org.invmgm.dto.InventoryItemCreateRequest;
import com.org.invmgm.dto.InventoryItemResponse;
import com.org.invmgm.dto.InventoryItemUpdateRequest;
import com.org.invmgm.service.impl.InventoryItemServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryItemController {

    private final InventoryItemServiceImpl service;

    public InventoryItemController(InventoryItemServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<InventoryItemResponse> receivedInventoryItem(@Valid @RequestBody InventoryItemCreateRequest request) {
        InventoryItemResponse response = service.receiveInventoryItem(request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<InventoryItemResponse> receivedInventoryItem(@PathVariable Long id, @Valid @RequestBody InventoryItemUpdateRequest request) {
        InventoryItemResponse response = service.updateInventoryItem(id, request);
        return ResponseEntity.ok(response);
    }
}
