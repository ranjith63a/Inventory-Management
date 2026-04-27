package com.org.invmgm.model;

import com.org.invmgm.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_transfer")
@Getter
@Setter
public class InventoryTransfer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_transfer_seq")
    @SequenceGenerator(sequenceName = "inventory_transfer_sequence", name = "inventory_transfer_seq", allocationSize = 1, initialValue = 10000)
    @Column(name = "inventory_transfer_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "transfer_reason_id", referencedColumnName = "enum_id", nullable = false)
    private Enumeration transferReason;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_item_id", nullable = false)
    private StatusItem status;

    private BigDecimal transferQuantity;

    @ManyToOne
    @JoinColumn(name = "transaction_uom_id", referencedColumnName = "uom_id", nullable = false)
    private Uom uom;

    @ManyToOne
    @JoinColumn(name =  "facility_id", nullable = false)
    private Facility facility;

    @ManyToOne
    @JoinColumn(name = "inventory_item_id", nullable = false)
    private InventoryItem inventoryItem;

    private String comments;

    
    private LocalDateTime transactionDate;

}
