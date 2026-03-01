package com.org.invmgm.repository;

import com.org.invmgm.model.StatusItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusItemTypeRepository extends JpaRepository<StatusItemType, String> {
}
