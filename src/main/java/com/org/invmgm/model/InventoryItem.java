package com.org.invmgm.model;

import com.org.invmgm.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_item")
@Getter
@Setter
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

    @ManyToOne
    @JoinColumn(name = "transaction_uom_id", referencedColumnName = "uom_id", nullable = false)
    private Uom uom;

    @ManyToOne
    @JoinColumn(name = "inventory_item_type_id", referencedColumnName = "enum_id", nullable = false)
    private Enumeration inventoryItemType;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_item_id", nullable = false)
    private StatusItem statusItem;

    private String lotId;
    private String comments;
    private String vendorId;
    private LocalDateTime receivedOn;
    private LocalDateTime transferredOn;
    private LocalDateTime verifiedOn;

}
