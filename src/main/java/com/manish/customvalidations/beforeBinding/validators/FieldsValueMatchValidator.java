package com.manish.customvalidations.beforeBinding.validators;

import com.manish.customvalidations.beforeBinding.FieldsValueMatch;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {

    private String message;
    private String field;
    private String fieldMatch;

    public void initialize(FieldsValueMatch constraintAnnotation) {
        this.message = constraintAnnotation.message();
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value)
          .getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value)
          .getPropertyValue(fieldMatch);

        if (Objects.equals(fieldValue, fieldMatchValue)) return true;

        HibernateConstraintValidatorContext hibernateConstraintValidatorContext =
                context.unwrap( HibernateConstraintValidatorContext.class );
        hibernateConstraintValidatorContext.addMessageParameter("first", fieldValue);
        hibernateConstraintValidatorContext.addMessageParameter("second", fieldMatchValue);

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addBeanNode()
                .addConstraintViolation();
        return false;
    }

}