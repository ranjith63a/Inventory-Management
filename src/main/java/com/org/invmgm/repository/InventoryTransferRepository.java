package com.org.invmgm.repository;

import com.org.invmgm.model.InventoryTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryTransferRepository extends JpaRepository<InventoryTransfer, Long> {
}
