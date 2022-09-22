package com.manish.customvalidations.beforeBinding;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface NameMaxLengths {
    NameMaxLength[] value();
}