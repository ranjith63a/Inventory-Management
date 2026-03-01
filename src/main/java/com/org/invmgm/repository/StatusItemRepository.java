package com.org.invmgm.repository;

import com.org.invmgm.model.StatusItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusItemRepository extends JpaRepository<StatusItem, String> {
}
