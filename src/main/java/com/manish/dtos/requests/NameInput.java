package com.manish.dtos.requests;

import com.manish.customvalidations.NameMaxLength;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

import static com.manish.constants.ValidationConstant.NAME_MAX_LENGTH;

@NoArgsConstructor
@Getter
@Setter
@NameMaxLength(maxLength = NAME_MAX_LENGTH, message = "{name.max.length}")
public class NameInput {
    @NotBlank(message = "{first.name.required}")
    private String first;

    private String middle;

    @NotBlank(message = "{last.name.required}")
    private String last;
}
