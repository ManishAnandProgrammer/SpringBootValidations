package com.manish.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
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
