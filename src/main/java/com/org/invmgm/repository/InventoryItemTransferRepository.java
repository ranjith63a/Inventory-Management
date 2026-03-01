package com.org.invmgm.repository;

import com.org.invmgm.model.InventoryItemTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryItemTransferRepository extends JpaRepository<InventoryItemTransfer, Long> {
}
