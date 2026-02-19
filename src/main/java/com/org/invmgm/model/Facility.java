package com.org.invmgm.model;

import jakarta.persistence.*;

@Entity
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "facility_seq")
    @SequenceGenerator(name = "facility_seq", sequenceName = "facility_sequence", initialValue = 10000, allocationSize = 1)
    @Column(name = "facilityId")
    private Long id;
    private String facilityName;
    private String description;

    public Facility(Long id, String facilityName, String description) {
        this.id = id;
        this.facilityName = facilityName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
