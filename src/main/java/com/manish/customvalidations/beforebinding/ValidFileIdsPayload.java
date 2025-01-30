package com.manish.customvalidations.beforebinding;

import com.manish.customvalidations.beforebinding.validators.FileIdsPayloadValidator;
import com.manish.customvalidations.group.OnUpdate;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = FileIdsPayloadValidator.class)
@Target({ ElementType.FIELD })
@Repeatable(ValidFileIdsPayload.List.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFileIdsPayload {

    String message() default "File Ids are not valid!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean optional();
    int min() default 0;
    int max() default Integer.MAX_VALUE;

    @Target({FIELD})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        ValidFileIdsPayload[] value();
    }

}