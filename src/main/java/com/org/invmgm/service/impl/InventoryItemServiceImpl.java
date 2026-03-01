package com.org.invmgm.service.impl;

import com.org.invmgm.dto.InventoryItemCreateRequest;
import com.org.invmgm.dto.InventoryItemResponse;
import com.org.invmgm.dto.InventoryItemUpdateRequest;
import com.org.invmgm.exception.DataNotFoundException;
import com.org.invmgm.model.*;
import com.org.invmgm.repository.*;
import com.org.invmgm.service.InventoryItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InventoryItemServiceImpl implements InventoryItemService {

    private final FacilityRepository fcRepo;
    private final ProductRepository proRepo;
    private final InventoryItemRepository invRepo;
    private final InventoryItemDetailRepository invDtlRepo;
    private final InventoryItemTransferRepository invTransRepo;
    private final UomRepository uomRepo;
    private final StatusItemRepository stsRepo;
    private final EnumerationRepository enumRepo;

    public InventoryItemServiceImpl(FacilityRepository fcRepo, ProductRepository proRepo, InventoryItemRepository invRepo, InventoryItemDetailRepository invDtlRepo, InventoryItemTransferRepository invTransRepo, UomRepository uomRepo, StatusItemRepository stsRepo, EnumerationRepository enumRepo) {
        this.fcRepo = fcRepo;
        this.proRepo = proRepo;
        this.invRepo = invRepo;
        this.invDtlRepo = invDtlRepo;
        this.invTransRepo = invTransRepo;
        this.uomRepo = uomRepo;
        this.stsRepo = stsRepo;
        this.enumRepo = enumRepo;
    }


    @Transactional
    @Override
    public InventoryItemResponse receiveInventoryItem(InventoryItemCreateRequest request) {

        // create inventory Item
        Map<String, Object> validated = validateInventoryItem(request.getFacilityId(), request.getProductId(), request.getTransactionUomId(), request.getStatusId());
        InventoryItem newItem = new InventoryItem();
        newItem.setProduct((Product) validated.get("product"));
        newItem.setFacility((Facility) validated.get("facility"));
        newItem.setUom((Uom) validated.get("uom"));
        newItem.setStatusItem((StatusItem) validated.get("statusItem"));
        newItem.setQuantity(convertStoredQuantity(request.getQuantity(), request.getTransactionUomId()));
        newItem.setLotId(request.getLotId());
        newItem.setComments(request.getComments());
        newItem.setLotId(request.getLotId());
        newItem.setEnumeration(enumRepo.findById("PURCHASED").orElseThrow(() -> new DataNotFoundException("Enumeration does not exists : PURCHASED")));
        newItem.setVendorId(request.getVendorId());
        newItem.setReceivedOn(LocalDateTime.now());
        InventoryItem result = invRepo.save(newItem);

        // create inventory detail
        createInventoryItemDetail(result.getId(), null, request.getQuantity(), request.getTransactionUomId(),"INV_PURCHASED");

        return responseMap(result);
    }

    @Override
    public InventoryItemResponse updateInventoryItem(Long id, InventoryItemUpdateRequest request) {
        return null;
    }

    private void createInventoryItemDetail(Long inventoryItemId, Long inventoryTransferId, BigDecimal quantity,
                                           String transactionUomId, String transactionTypeId) {

        InventoryItemDetail detail = new InventoryItemDetail();
        detail.setItem(invRepo.findById(inventoryItemId).orElseThrow(() -> new DataNotFoundException("Inventory Item does exists Id : " + inventoryItemId)));
        detail.setEnumeration(enumRepo.findById(transactionTypeId).orElseThrow(() -> new DataNotFoundException("Enumeration does not exists : " + transactionTypeId)));
        if (inventoryTransferId != null) {
            detail.setTransfer(invTransRepo.findById(inventoryTransferId).orElseThrow(() -> new DataNotFoundException("Transfer does not exists : " + inventoryTransferId)));
        }
        detail.setQuantity(convertStoredQuantity(quantity, transactionUomId));
        detail.setTransactionUomId(transactionUomId);
        detail.setTransactionDate(LocalDateTime.now());

        List<InventoryItemDetail> itemDetailList = invDtlRepo.findByIdOrderByTransactionDateDesc(invRepo.findById(inventoryItemId).orElseThrow(() -> new DataNotFoundException("Inventory Item does exists Id : " + inventoryItemId)));
        if (!itemDetailList.isEmpty()) {
            detail.setBalanceQuantity(itemDetailList.get(0).getBalanceQuantity().subtract(convertStoredQuantity(quantity, transactionUomId)));
        } else {
            detail.setBalanceQuantity(convertStoredQuantity(quantity, transactionUomId));
        }

        invDtlRepo.save(detail);
    }

    private BigDecimal convertStoredQuantity(BigDecimal quantity, String transactionUomId) {
        Uom uom = uomRepo.findById(transactionUomId).orElseThrow(() -> new DataNotFoundException("Uom does not exists Id : " + transactionUomId));
        return uom.getConversion().multiply(quantity);
    }
    private BigDecimal convertDisplayQuantity(BigDecimal quantity, String transactionUomId) {
        Uom uom = uomRepo.findById(transactionUomId).orElseThrow(() -> new DataNotFoundException("Uom does not exists Id : " + transactionUomId));
        return uom.getConversion().divide(quantity, RoundingMode.HALF_UP);
    }
    private Map<String, Object> validateInventoryItem(Long facilityId, Long productId, String transactionUomId, String statusId) {

        Map<String, Object> validateMap = new HashMap<>();
        Facility facility = fcRepo.findById(facilityId).orElseThrow(() -> new DataNotFoundException("Facility Id does not exists Id : " + facilityId));
        validateMap.put("facility", facility);

        Product product = proRepo.findById(productId).orElseThrow(() -> new DataNotFoundException("Product does not exists Id : " + productId));
        validateMap.put("product", product);

        Uom uom = uomRepo.findById(transactionUomId).orElseThrow(() -> new DataNotFoundException("Uom does not exists Id : " + transactionUomId));
        validateMap.put("uom", uom);

        StatusItem statusItem = stsRepo.findById(statusId).orElseThrow(() -> new DataNotFoundException("Status does not exists id : " + statusId));
        validateMap.put("statusItem", statusItem);

        return validateMap;
    }

    private InventoryItemResponse responseMap(InventoryItem item) {
        return new InventoryItemResponse(item);
    }
}
