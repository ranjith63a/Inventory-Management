package com.org.invmgm.model;

import com.org.invmgm.common.entity.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "inventory_item")
public class InventoryItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventoryItem_seq")
    @SequenceGenerator(name = "inventoryItem_seq", sequenceName ="inventoryItem_sequence", initialValue = 10000, allocationSize = 1)
    @Column(name = "inventory_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "facility_id", nullable = false)
    private Facility facility;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private BigDecimal quantity;
    private String transactionUomId;
    private String lotId;
    private String comments;
    private String vendorId;
    private Timestamp receivedOn;
    private Timestamp transferredOn;
    private Timestamp verifiedBy;

    public InventoryItem(Long id, BigDecimal quantity, Timestamp verifiedBy) {
        this.id = id;
        this.quantity = quantity;
        this.verifiedBy = verifiedBy;
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
