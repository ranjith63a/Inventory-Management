package com.org.invmgm.repository;

import com.org.invmgm.model.InventoryItem;
import com.org.invmgm.model.InventoryItemDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryItemDetailRepository extends JpaRepository<InventoryItemDetail, Long> {

    Optional<InventoryItemDetail> findTopByItemOrderByTransactionDateDesc(InventoryItem item);
}
