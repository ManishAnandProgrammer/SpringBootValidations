package com.manish.dtos.requests;

import com.manish.enums.EmailType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class EmailInput {
    @NotNull
    private EmailType emailType;

    @NotBlank
    @Email
    private String emailAddress;
}
