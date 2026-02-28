package com.org.invmgm.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacilityRequest {

    @NotBlank(message = "Facility Name cannot be empty")
    private String facilityName;
    private String description;
}
