package com.manish.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class DriverResponse {
    private Long id;
    private String name;
    private Long profilePictureId;
    private Set<Long> licenseIds;
    private Set<Long> personalDocumentIds;

    public void addLicenseIds(Set<Long> licenseIds) {
        this.licenseIds = licenseIds;
    }

    public void addPersonalDocumentIds(Set<Long> personalDocumentIds) {
        this.personalDocumentIds = personalDocumentIds;
    }

}
