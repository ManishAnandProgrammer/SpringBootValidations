package com.manish.customvalidations;

import com.manish.dtos.requests.NameInput;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class NameMaxLengthValidator implements ConstraintValidator<NameMaxLength, NameInput> {

    public static final int DEFAULT_LENGTH_FOR_NULL = 0;
    private String message;
    private int maxLength;

    @Override
    public void initialize(NameMaxLength constraintAnnotation) {
        message = constraintAnnotation.message();
        maxLength = constraintAnnotation.maxLength();
    }

    @Override
    public boolean isValid(NameInput nameInput, ConstraintValidatorContext context) {
        if (Objects.isNull(nameInput)) return true;
        if (isValidNameLength(getTotalNameLengthProvided(nameInput))) return true;

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addBeanNode()
                .addConstraintViolation();
        return false;
    }

    private int getTotalNameLengthProvided(NameInput nameInput) {
        int firstNameLength = lengthOfStringAssumingNullAsZero(nameInput.getFirst());
        int middleNameLength = lengthOfStringAssumingNullAsZero(nameInput.getMiddle());
        int lastNameLength = lengthOfStringAssumingNullAsZero(nameInput.getLast());

        return firstNameLength + middleNameLength + lastNameLength;
    }

    private boolean isValidNameLength(int totalNameLengthProvided) {
        return totalNameLengthProvided < maxLength;
    }

    private int lengthOfStringAssumingNullAsZero(String value) {
        return Objects.isNull(value) ? DEFAULT_LENGTH_FOR_NULL : value.length();
    }
}
