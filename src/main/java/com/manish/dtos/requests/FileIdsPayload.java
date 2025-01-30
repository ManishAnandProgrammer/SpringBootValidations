package com.manish.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.SetUtils;

import java.util.Set;

@Setter
public class FileIdsPayload {
    private Set<Long> oldIds;
    private Set<Long> newIds;
    private Set<Long> deletedIds;

    public Set<Long> getOldIds() {
        return SetUtils.emptyIfNull(oldIds);
    }

    public Set<Long> getNewIds() {
        return SetUtils.emptyIfNull(newIds);
    }

    public Set<Long> getDeletedIds() {
        return SetUtils.emptyIfNull(deletedIds);
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