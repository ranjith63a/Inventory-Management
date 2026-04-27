package com.org.invmgm.model;

import com.org.invmgm.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_item_detail")
@Getter
@Setter
public class InventoryItemDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventoryItemDetail_seq")
    @SequenceGenerator(name = "inventoryItemDetail_seq", sequenceName = "inventoryItemDetail_sequence", initialValue = 10000, allocationSize = 1)
    @Column(name = "inventory_item_seq_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inventory_item_id", nullable = false)
    private InventoryItem item;

    @ManyToOne
    @JoinColumn(name = "inventory_item_transfer_id")
    private InventoryTransfer transfer;

    private BigDecimal quantity;
    private String transactionUomId;
    private LocalDateTime transactionDate;
    private BigDecimal balanceQuantity;

    @ManyToOne
    @JoinColumn(name = "transaction_type_id",  referencedColumnName = "enum_id",  nullable = false)
    private Enumeration transactionTypeId;

}
