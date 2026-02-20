package com.org.invmgm.repository;

import com.org.invmgm.model.Uom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UomRepository extends JpaRepository<Uom, Long> {
    Optional<Uom> findByIdEquals(String id);
}
