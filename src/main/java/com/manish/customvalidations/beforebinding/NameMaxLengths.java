package com.manish.customvalidations.beforebinding;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface NameMaxLengths {
    NameMaxLength[] value();
}