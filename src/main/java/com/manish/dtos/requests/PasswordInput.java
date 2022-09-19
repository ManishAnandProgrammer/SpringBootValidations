package com.manish.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PasswordInput {
    @NotBlank(message = "{password.required}")
    private String password;

    @NotBlank(message = "{confirm.password.required}")
    private String confirmPassword;
}
