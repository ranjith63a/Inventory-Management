package com.org.invmgm.repository;

import com.org.invmgm.model.Enumeration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnumerationRepository extends JpaRepository<Enumeration, String> {
}
