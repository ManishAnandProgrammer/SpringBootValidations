package com.manish.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private final Date timestamp;
    private final int status;
    private final String message;
    private final String details;
    private String stackTrace;
    private List<ValidationError> errors;
    private record ValidationError(String field, String message, Object rejectedValue) {}

    public void addValidationError(FieldError fieldError){
        if(Objects.isNull(errors)){
            errors = new ArrayList<>();
        }
        errors.add(fieldErrorToValidationError(fieldError));
    }

    private ValidationError fieldErrorToValidationError(FieldError fieldError) {
        return new ValidationError(
                fieldError.getField(),
                fieldError.getDefaultMessage(),
                fieldError.getRejectedValue()
        );
    }
}