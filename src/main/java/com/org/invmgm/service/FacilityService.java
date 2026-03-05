package com.org.invmgm.service;

import com.org.invmgm.dto.FacilityRequest;
import com.org.invmgm.dto.FacilityResponse;
import com.org.invmgm.model.Facility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FacilityService {
    FacilityResponse createFacility(FacilityRequest facilityRequest);
    FacilityResponse updateFacility(Long id, FacilityRequest facilityRequest);
    FacilityResponse getFacility(Long id);
    Page<FacilityResponse> getListOfFacility(Long id, String facilityName, Pageable pageable);
    FacilityResponse responseMap(Facility facility);
}
