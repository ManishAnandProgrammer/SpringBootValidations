package com.manish.controllers;

import com.manish.customvalidations.group.OnCreate;
import com.manish.customvalidations.group.OnUpdate;
import com.manish.dtos.requests.DriverPayload;
import com.manish.dtos.requests.FileIdsPayload;
import com.manish.dtos.responses.DriverResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@Slf4j
public class DriverController {
    private static final AtomicLong ID_GENERATOR = new AtomicLong();
    private static final List<DriverResponse> DRIVERS = new CopyOnWriteArrayList<>();

    @PostMapping("/drivers")
    public DriverResponse createDriver(@Validated(OnCreate.class) @RequestBody DriverPayload payload) {
        log.info("Creating driver with name: {}", payload.getName());

        return whenCreating(payload);
    }

    @GetMapping("/drivers/{id}")
    public DriverResponse getDriver(@PathVariable Long id) {
        log.info("Getting driver with id: {}", id);

        return DRIVERS.stream()
                .filter(driver -> driver.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Driver not found with id: " + id));
    }

    @PutMapping("/drivers/{id}")
    public DriverResponse updateDriver(@PathVariable Long id,
                                       @Validated(OnUpdate.class) @RequestBody DriverPayload payload) {
        log.info("Updating driver with id: {}", id);

        return whenUpdating(id, payload);
    }

    private static DriverResponse whenCreating(DriverPayload payload) {
        DriverResponse response = new DriverResponse();

        response.setId(ID_GENERATOR.incrementAndGet());
        response.setName(payload.getName());

        FileIdsPayload profilePicture = payload.getProfilePicture();
        Set<Long> profilePictureIds = profilePicture.effectivelyActiveIds();
        // Here we know that profilePictureIds will always have at least one element
        response.setProfilePictureId(IterableUtils.get(profilePictureIds, 0));


        FileIdsPayload license = payload.getLicense();
        Set<Long> licenceIds = license.effectivelyActiveIds();
        response.setLicenseIds(licenceIds);

        FileIdsPayload personalDocuments = payload.getPersonalDocuments();
        Set<Long> personalDocumentIds = personalDocuments.effectivelyActiveIds();
        response.setPersonalDocumentIds(personalDocumentIds);

        DRIVERS.add(response);
        return response;
    }

    private static DriverResponse whenUpdating(Long id, DriverPayload payload) {
        DriverResponse response = DRIVERS.stream()
                .filter(driver -> driver.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Driver not found with id: " + id));

        response.setName(payload.getName());

        // ----------------- Profile Picture -----------------
        FileIdsPayload profilePicture = payload.getProfilePicture();
        if (CollectionUtils.isNotEmpty(profilePicture.getDeletedIds())) {
            deleteOldProfilePicture(profilePicture.getDeletedIds());
        }
        if (CollectionUtils.isNotEmpty(profilePicture.getNewIds())) {
            attachNewProfilePicture(response, profilePicture.getNewIds());
        }

        // ----------------- License -----------------
        FileIdsPayload license = payload.getLicense();
        if (CollectionUtils.isNotEmpty(license.getDeletedIds())) {
            deleteOldLicence(response, license.getOldIds());
        }
        if (CollectionUtils.isNotEmpty(license.getNewIds())) {
            response.addLicenseIds(license.getNewIds());
        }

        // ----------------- Personal Documents -----------------
        FileIdsPayload personalDocuments = payload.getPersonalDocuments();
        if (CollectionUtils.isNotEmpty(personalDocuments.getDeletedIds())) {
            deletePersonalDocuments(response, personalDocuments);
        }
        if (CollectionUtils.isNotEmpty(personalDocuments.getNewIds())) {
            response.addPersonalDocumentIds(personalDocuments.getNewIds());
        }

        return response;
    }

    private static void deletePersonalDocuments(DriverResponse response,
                                                FileIdsPayload personalDocuments) {
        // Delete the old personal documents
        log.info("Deleting old personal documents with ids: {}", personalDocuments.getDeletedIds());
        // Here we need to check that user has permission to delete these files
        // like he is the owner of the file or admin.

        response.getPersonalDocumentIds().removeAll(personalDocuments.getDeletedIds());
    }

    private static void deleteOldLicence(DriverResponse response, Set<Long> oldIds) {
        // Delete the old license
        log.info("Deleting old license with ids: {}", oldIds);
        // Here we need to check that user has permission to delete these files
        // like he is the owner of the file or admin.

        response.getLicenseIds().removeAll(oldIds);
    }

    private static void attachNewProfilePicture(DriverResponse response, Set<Long> newIds) {
        // Attach the new profile picture
        log.info("Attaching new profile picture with ids: {}", newIds);
        Long id = IterableUtils.get(newIds, 0);
        response.setProfilePictureId(id);
        // Here we need to check that user has permission to attach these files
        // like he is the owner of the file or admin.
    }

    private static void deleteOldProfilePicture(Set<Long> deletedIds) {
        // Delete the old profile picture
        log.info("Deleting old profile picture with ids: {}", deletedIds);
        // Here we need to check that user has permission to delete these files
        // like he is the owner of the file or admin.
    }

}
