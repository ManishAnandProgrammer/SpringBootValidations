package com.manish.dtos.requests;

import com.manish.customvalidations.beforebinding.ValidFileIdsPayload;
import com.manish.customvalidations.group.OnCreate;
import com.manish.customvalidations.group.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverPayload {

    @NotBlank
    private String name;

    @ValidFileIdsPayload(min = 1, max = 1, optional = false, groups = OnCreate.class)
    @ValidFileIdsPayload(min = 1, max = 1, optional = false, groups = OnUpdate.class)
    private FileIdsPayload profilePicture;

    @ValidFileIdsPayload(min = 2, max = 2, optional = true, groups = OnCreate.class)
    @ValidFileIdsPayload(min = 2, max = 2, optional = true, groups = OnUpdate.class)
    private FileIdsPayload license;

    @ValidFileIdsPayload(min = 3, max = 5, optional = false, groups = OnCreate.class)
    @ValidFileIdsPayload(min = 3, max = 5, optional = false, groups = OnUpdate.class)
    private FileIdsPayload personalDocuments;

}
