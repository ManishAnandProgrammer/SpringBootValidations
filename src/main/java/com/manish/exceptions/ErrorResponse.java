package com.manish.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class ErrorResponse {
    private final Date timestamp;
    private final int status;
    private final String message;
    private final String details;
    private String stackTrace;
    private List<ValidationError> errors;

    @Getter
    @Setter
    @RequiredArgsConstructor
    @ToString
    private static class ValidationError {
        private final String field;
        private final String message;
        private final Object rejectedValue;
    }

    public void addValidationError(String field, String message, Object rejectedValue){
        if(Objects.isNull(errors)){
            errors = new ArrayList<>();
        }
        errors.add(new ValidationError(field, message, rejectedValue));
    }
}