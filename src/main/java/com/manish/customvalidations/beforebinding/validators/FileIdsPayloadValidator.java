package com.manish.customvalidations.beforebinding.validators;

import com.manish.customvalidations.beforebinding.ValidFileIdsPayload;
import com.manish.customvalidations.group.OnCreate;
import com.manish.customvalidations.group.OnUpdate;
import com.manish.dtos.requests.FileIdsPayload;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Range;

import java.util.Objects;
import java.util.Set;

public class FileIdsPayloadValidator implements ConstraintValidator<ValidFileIdsPayload, FileIdsPayload> {
    private static final String FILE_COUNT_NOT_VALID =
            "File Ids count should be between %d and %d, But found %d";

    private int min;
    private int max;
    private boolean optional;
    private Class<?>[] groups;

    @Override
    public void initialize(ValidFileIdsPayload constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
        this.optional = constraintAnnotation.optional();
        this.groups = constraintAnnotation.groups();
    }

    @Override
    public boolean isValid(FileIdsPayload value, ConstraintValidatorContext context) {
        if (Objects.isNull(value) && optional) {
            return true;
        }

        if (Objects.isNull(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File Ids Should Not Be Null")
                    .addConstraintViolation();
            return false;
        }

        if (!payloadValidAccordingToGroup(value, context)) {
            return false;
        }

        return fileCountSatisfy(value, context);
    }

    private boolean payloadValidAccordingToGroup(
            FileIdsPayload value, ConstraintValidatorContext context) {

        if (groups.length != 1) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Only One Group Should Be Provided")
                    .addConstraintViolation();
            return false;
        }

        Class<?> group = groups[0];
        if (group.equals(OnCreate.class)) {
            return validAccordingToCreateGroup(value, context);
        } else if (group.equals(OnUpdate.class)) {
            return validAccordingToUpdateGroup(value, context);
        } else {
            return invalidGroupProvided(context);
        }
    }

    private boolean validAccordingToCreateGroup(FileIdsPayload value,
                                                ConstraintValidatorContext context) {
        Set<Long> oldIds = value.getOldIds();
        if (CollectionUtils.isNotEmpty(oldIds)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Old Ids Should Be Empty When Creating Data")
                    .addConstraintViolation();
            return false;
        }

        Set<Long> deletedIds = value.getDeletedIds();
        if (CollectionUtils.isNotEmpty(deletedIds)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Deleted Ids Should Be Empty When Creating Data")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

    private boolean validAccordingToUpdateGroup(FileIdsPayload value,
                                                ConstraintValidatorContext context) {
        if (!deletedIdsPresentInOldIds(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Deleted Ids Should Be Present In Old Ids")
                    .addConstraintViolation();
            return false;
        }

        if (newIdsFoundInOld(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("New Ids Should Not Present In Old Or Deleted Ids")
                    .addConstraintViolation();
            return false;
        }

        if (newIdsFoundInDeleted(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("New Ids Should Not Present In Old Or Deleted Ids")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

    private boolean deletedIdsPresentInOldIds(FileIdsPayload value) {
        if (CollectionUtils.isEmpty(value.getDeletedIds())) {
            // If deletedIds is empty, then return true
            // because if deletedIds is empty,
            // then there is no need to check if deletedIds are present in oldIds
            return true;
        }

        return value.getOldIds().containsAll(value.getDeletedIds());
    }

    private boolean newIdsFoundInOld(FileIdsPayload value) {
        if (CollectionUtils.isEmpty(value.getNewIds())) {
            // If newIds is empty, then return false
            // because if newIds is empty,
            // then there is no need to check if newIds are present in oldIds
            return false;
        }

        return CollectionUtils.containsAny(value.getOldIds(), value.getNewIds());
    }

    private boolean newIdsFoundInDeleted(FileIdsPayload value) {
        if (CollectionUtils.isEmpty(value.getNewIds())) {
            // If newIds is empty, then return false
            // because if newIds is empty,
            // then there is no need to check if newIds are present in deletedIds
            return false;
        }

        return CollectionUtils.containsAny(value.getDeletedIds(), value.getNewIds());
    }

    private boolean fileCountSatisfy(FileIdsPayload value, ConstraintValidatorContext context) {
        Set<Long> effectivelyActiveIds = value.effectivelyActiveIds();
        int size = effectivelyActiveIds.size();
        if (optional && size == 0) {
            return true;
        }

        boolean validFileCount = Range.of(min, max).contains(size);
        if (validFileCount) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        String messageTemplate = FILE_COUNT_NOT_VALID.formatted(min, max, size);
        context.buildConstraintViolationWithTemplate(messageTemplate).addConstraintViolation();
        return false;
    }

    private boolean invalidGroupProvided(ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Invalid Group Provided")
                .addConstraintViolation();
        return false;
    }

}