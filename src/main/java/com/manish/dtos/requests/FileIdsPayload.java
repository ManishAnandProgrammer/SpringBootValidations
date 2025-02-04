package com.manish.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.SetUtils;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
public class FileIdsPayload {
    private Set<Long> oldIds;
    private Set<Long> newIds;
    private Set<Long> deletedIds;

    public Set<Long> getOldIds() {
        return SetUtils.emptyIfNull(oldIds).stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    public Set<Long> getNewIds() {
        return SetUtils.emptyIfNull(newIds).stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    public Set<Long> getDeletedIds() {
        return SetUtils.emptyIfNull(deletedIds).stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    @JsonIgnore
    public Set<Long> effectivelyActiveIds() {
        if (CollectionUtils.isEmpty(getOldIds())) {
            // If oldIds is empty, then return newIds as it is
            // because if oldIds is empty,
            // then there is no need to remove deletedIds from it
            return getNewIds();
        }

        // Remove deletedIds from oldIds
        SetUtils.SetView<Long> oldIdsAfterRemovingDeletedOnes =
                SetUtils.difference(getOldIds(), getDeletedIds());

        // Return union of oldIdsAfterRemovingDeletedOnes and newIds
        // these are the effectively active ids.
        return SetUtils.union(oldIdsAfterRemovingDeletedOnes, getNewIds());
    }

}