package com.manish.dtos.requests;

import com.manish.customvalidations.beforeBinding.FieldsValueMatch;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@FieldsValueMatch(field = "password", fieldMatch = "confirmPassword", message = "{field.value.mismatch}")
public class PasswordInput {

    @NotBlank(message = "{password.required}")
    private String password;

    @NotBlank(message = "{confirm.password.required}")
    private String confirmPassword;
}
