package com.manish.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class NameInput {
    @NotBlank(message = "{first.name.required}")
    private String first;

    private String middle;

    @NotBlank(message = "{last.name.required}")
    private String last;
}
