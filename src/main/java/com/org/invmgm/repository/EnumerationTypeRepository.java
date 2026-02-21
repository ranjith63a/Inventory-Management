package com.org.invmgm.repository;

import com.org.invmgm.model.EnumerationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnumerationTypeRepository extends JpaRepository<EnumerationType, String> {
}
