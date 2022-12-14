package com.manish.dtos.requests;

import com.manish.customvalidations.beforeBinding.FieldsValueMatch;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@FieldsValueMatch(field = "password", fieldMatch = "confirmPassword", message = "{field.value.mismatch}")
public class PasswordInput {
    @NotBlank(message = "{password.required}")
    private String password;

    @NotBlank(message = "{confirm.password.required}")
    private String confirmPassword;
}
