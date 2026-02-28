package com.org.invmgm.service.impl;

import com.org.invmgm.dto.FacilityRequest;
import com.org.invmgm.dto.FacilityResponse;
import com.org.invmgm.exception.DataNotFoundException;
import com.org.invmgm.model.Facility;
import com.org.invmgm.repository.FacilityRepository;
import com.org.invmgm.service.FacilityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacilityServiceImpl implements FacilityService {

    private final FacilityRepository fcRepo;

    public FacilityServiceImpl(FacilityRepository fcRepo) {
        this.fcRepo = fcRepo;
    }

    @Transactional
    @Override
    public FacilityResponse createFacility(FacilityRequest facilityRequest) {

        Facility facility = new Facility();

        facility.setFacilityName(facilityRequest.getFacilityName());

        if (facilityRequest.getDescription() != null) {
            facility.setDescription(facilityRequest.getDescription());
        }

        Facility response = fcRepo.save(facility);
        return responseMap(response);
    }

    @Transactional
    @Override
    public FacilityResponse updateFacility(Long id, FacilityRequest facilityRequest) {

        Facility facility = fcRepo.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Facility Id does not exists"));

        facility.setFacilityName(facilityRequest.getFacilityName());

        if (facilityRequest.getDescription() != null) {
            facility.setDescription(facilityRequest.getDescription());
        }

        // we don't want to save @Transactional will save
        //Facility response = fcRepo.save(facility);

        return responseMap(facility);
    }

    @Override
    public FacilityResponse getFacility(Long id) {

        Facility facility = fcRepo.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Facility Id does not exists"));

        return responseMap(facility);
    }

    @Override
    public Page<FacilityResponse> getListOfFacility(Long id, String facilityName, Pageable pageable) {


        Page<Facility> response;
        if (id != null && facilityName != null) {
            response = fcRepo.findByIdEqualsAndFacilityNameContainingIgnoreCase(id, facilityName, pageable);
        } else if (id != null) {
            response = fcRepo.findByIdEquals(id, pageable);
        } else if (facilityName != null) {
            response = fcRepo.findByFacilityNameEquals(facilityName, pageable);
        } else {
            response = fcRepo.findAll(pageable);
        }
        return response.map(this::responseMap);
    }

    private FacilityResponse responseMap(Facility facility) {
        FacilityResponse response = new FacilityResponse();
        response.setId(facility.getId());
        response.setFacilityName(facility.getFacilityName());
        response.setDescription(facility.getDescription());
        return response;
    }
}
