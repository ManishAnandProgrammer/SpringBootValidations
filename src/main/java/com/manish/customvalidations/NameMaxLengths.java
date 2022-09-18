package com.manish.customvalidations;

import com.manish.customvalidations.NameMaxLength;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface NameMaxLengths {
    NameMaxLength[] value();
}