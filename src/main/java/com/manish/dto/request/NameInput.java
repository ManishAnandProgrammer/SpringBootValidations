package com.manish.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class NameInput {
    @NotBlank
    private String first;

    private String middle;

    @NotBlank
    private String last;
}
