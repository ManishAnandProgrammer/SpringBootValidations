package com.manish.customvalidations.beforeBinding;

import com.manish.customvalidations.beforeBinding.validators.NameMaxLengthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NameMaxLengthValidator.class)
@Target( { ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(NameMaxLengths.class)
public @interface NameMaxLength {
    String message() default "Name Max Length Exceeded";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int maxLength();
}
