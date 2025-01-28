package com.manish.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class PhoneNumberInput {

    @NotBlank
    private String countryDialCode;

    @NotBlank
    private String number;
}
