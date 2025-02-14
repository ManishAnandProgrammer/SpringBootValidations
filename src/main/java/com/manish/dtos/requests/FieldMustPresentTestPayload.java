package com.manish.dtos.requests;

import com.manish.customvalidations.beforebinding.OnlyOneOfTheFieldsMustPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@OnlyOneOfTheFieldsMustPresent(
    fields = { "phoneNumber", "email", "mobileNumber" },
    not = OnlyOneOfTheFieldsMustPresent.Not.BLANK
)
@OnlyOneOfTheFieldsMustPresent(
    fields = { "fatherDOB", "motherDOB", "guardianDOB" },
    not = OnlyOneOfTheFieldsMustPresent.Not.NULL
)
@OnlyOneOfTheFieldsMustPresent(
    fields = { "names", "homeAddresses", "workAddresses" },
    not = OnlyOneOfTheFieldsMustPresent.Not.EMPTY
)
@Getter
@Setter
public class FieldMustPresentTestPayload {
    private String phoneNumber;
    private String email;
    private String mobileNumber;

    private LocalDate fatherDOB;
    private LocalDate motherDOB;
    private LocalDate guardianDOB;

    private List<String> names;
    private List<String> homeAddresses;
    private List<String> workAddresses;
}
