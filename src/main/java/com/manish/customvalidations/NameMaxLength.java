package com.manish.customvalidations;

import javax.validation.Constraint;
import javax.validation.Payload;
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