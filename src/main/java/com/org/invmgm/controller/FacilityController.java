package com.org.invmgm.controller;

import com.org.invmgm.dto.FacilityRequest;
import com.org.invmgm.dto.FacilityResponse;
import com.org.invmgm.service.impl.FacilityServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/facility")
public class FacilityController {

    private final FacilityServiceImpl facilityService;

    public FacilityController(FacilityServiceImpl facilityService) {
        this.facilityService = facilityService;
    }

    @PostMapping
    public ResponseEntity<FacilityResponse> create(@Valid @RequestBody FacilityRequest request) {
        FacilityResponse response = facilityService.createFacility(request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FacilityResponse> update(@PathVariable Long id, @Valid @RequestBody FacilityRequest request) {
        FacilityResponse response = facilityService.updateFacility(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacilityResponse> getFacility(@RequestParam Long id) {
        FacilityResponse response = facilityService.getFacility(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<FacilityResponse>> getListOfFacility(@RequestParam(required = false) Long id, @RequestParam(required = false) String facilityName, Pageable pageable) {
        Page<FacilityResponse> response = facilityService.getListOfFacility(id, facilityName, pageable);
        return ResponseEntity.ok(response);
    }
}
