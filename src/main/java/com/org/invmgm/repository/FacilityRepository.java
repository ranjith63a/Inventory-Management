package com.org.invmgm.repository;

import com.org.invmgm.dto.FacilityResponse;
import com.org.invmgm.model.Facility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {
    Page<Facility> findByIdEquals(Long id, Pageable pageable);
    Page<Facility> findByFacilityNameEquals(String facilityName, Pageable pageable);
    Page<Facility> findByIdEqualsAndFacilityNameContainingIgnoreCase(Long id, String facilityName, Pageable pageable);
}
