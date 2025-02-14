package com.manish.customvalidations.beforebinding;

import com.manish.customvalidations.beforebinding.validators.OnlyOneOfTheFieldMustPresentValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OnlyOneOfTheFieldMustPresentValidator.class)
@Target({ ElementType.TYPE })
@Repeatable(OnlyOneOfTheFieldsMustPresent.List.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface OnlyOneOfTheFieldsMustPresent {
    String message() default "Only one of the fields must be present!";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};

    String[] fields();
    Not not() default Not.NULL;

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        OnlyOneOfTheFieldsMustPresent[] value();
    }

    enum Not {
        NULL,
        EMPTY,
        BLANK
    }
}
