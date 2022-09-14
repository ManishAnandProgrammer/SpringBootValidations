package com.manish.domain.embedded;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class PhoneNumber {
    private String countryDialCode;
    private String number;
}
