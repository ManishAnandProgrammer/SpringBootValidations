package com.manish.dtos.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserUpsertRequest {

    private Long id;

    @Valid
    @NotNull
    private NameInput name;

    @Valid
    @NotNull
    private PhoneNumberInput phoneNumber;

    @Valid
    private List<EmailInput> emails;
}
