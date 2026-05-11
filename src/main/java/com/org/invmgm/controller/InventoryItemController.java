package com.org.invmgm.controller;

import com.org.invmgm.dto.*;
import com.org.invmgm.service.impl.FileServiceImpl;
import com.org.invmgm.service.impl.InventoryItemServiceImpl;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryItemController {

    private final InventoryItemServiceImpl service;
    private final FileServiceImpl fileService;

    public InventoryItemController(InventoryItemServiceImpl service, FileServiceImpl fileService) {
        this.service = service;
        this.fileService = fileService;
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
    public ResponseEntity<Page<InventoryItemResponse>> findAllInventory(@ParameterObject Pageable pageable) {
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


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws IOException {

        return ResponseEntity.ok(fileService.upload(file));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id) throws IOException {

        CustomFileResource file = fileService.download(id);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFileName() + "\"")
                .body(file.getResource());
    }
}
