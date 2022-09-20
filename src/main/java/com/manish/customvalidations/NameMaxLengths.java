package com.manish.customvalidations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface NameMaxLengths {
    NameMaxLength[] value();
}