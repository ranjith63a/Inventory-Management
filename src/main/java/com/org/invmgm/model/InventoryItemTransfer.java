package com.org.invmgm.model;

import com.org.invmgm.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "inventory_item_transfer")
@Getter
@Setter
public class InventoryItemTransfer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventoryItemTransfer_seq")
    @SequenceGenerator(name = "inventoryItemTransfer_seq", sequenceName = "inventoryItemTransfer_sequence", initialValue = 10000, allocationSize = 1)
    @Column(name = "inventory_item_transfer_id")
    private Long id;

    private BigDecimal transferQuantity;
    private BigDecimal transactionUomId;

}
