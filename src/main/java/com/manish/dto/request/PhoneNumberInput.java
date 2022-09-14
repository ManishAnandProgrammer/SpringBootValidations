package com.manish.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class PhoneNumberInput {

    @NotBlank
    private String countryDialCode;

    @NotBlank
    private String number;
}
