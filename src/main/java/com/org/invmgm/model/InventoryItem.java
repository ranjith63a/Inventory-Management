package com.org.invmgm.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventoryItem_seq")
    @SequenceGenerator(name = "inventoryItem_seq", sequenceName ="inventoryItem_sequence", initialValue = 10000, allocationSize = 1)
    @Column(name = "inventoryItemId")
    private Long id;

    private Long facilityId;
    private Long productId;
    private BigDecimal quantity;
    private String transactionUomId;
    private String lotId;
    private String comments;
    private String vendorId;
    private Timestamp receivedOn;
    private Timestamp transferredOn;
    private Timestamp verifiedBy;

    public InventoryItem(Long id, Long facilityId, Long productId, BigDecimal quantity, Timestamp verifiedBy) {
        this.id = id;
        this.facilityId = facilityId;
        this.productId = productId;
        this.quantity = quantity;
        this.verifiedBy = verifiedBy;
    }

    public Long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getTransactionUomId() {
        return transactionUomId;
    }

    public void setTransactionUomId(String transactionUomId) {
        this.transactionUomId = transactionUomId;
    }

    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String ventorId) {
        this.vendorId = ventorId;
    }

    public Timestamp getReceivedOn() {
        return receivedOn;
    }

    public void setReceivedOn(Timestamp receivedOn) {
        this.receivedOn = receivedOn;
    }

    public Timestamp getTransferredOn() {
        return transferredOn;
    }

    public void setTransferredOn(Timestamp transferredOn) {
        this.transferredOn = transferredOn;
    }

    public Timestamp getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(Timestamp verifiedBy) {
        this.verifiedBy = verifiedBy;
    }
}
