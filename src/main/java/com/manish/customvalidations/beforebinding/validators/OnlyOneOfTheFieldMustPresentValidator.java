package com.manish.customvalidations.beforebinding.validators;

import com.manish.customvalidations.beforebinding.OnlyOneOfTheFieldsMustPresent;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Objects;
import java.util.function.BiPredicate;

@Slf4j
public class OnlyOneOfTheFieldMustPresentValidator
        implements ConstraintValidator<OnlyOneOfTheFieldsMustPresent, Object> {
    private static final String UNDER_REQUIRED_ERROR_TEMPLATE =
            "At least one of the fields[%s] must be %s";
    private static final String OVER_REQUIRED_ERROR_TEMPLATE =
            "Only one of the fields [%s] must be %s";

    private static final int MIN_REQUIRED_FIELD_COUNT = 2;
    private static final int PRESENT_FIELD_COUNT_MUST_BE = 1;

    private String[] fields;
    private OnlyOneOfTheFieldsMustPresent.Not mustNot;

    @Override
    public void initialize(OnlyOneOfTheFieldsMustPresent constraintAnnotation) {
        this.fields = constraintAnnotation.fields();
        this.mustNot = constraintAnnotation.not();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        long providedFieldsCount = ArrayUtils.getLength(fields);
        if (providedFieldsCount < MIN_REQUIRED_FIELD_COUNT) {
            // No need to validate if there are less than 2 fields
            // Let @NotNull, @NotBlank or @Size handle it.
            return true;
        }


        BiPredicate<Object, String> predicate = validateFunction();
        Bag<Boolean> validationResults = new HashBag<>();
        for (String fieldName: fields) {
            boolean testResult = predicate.test(value, fieldName);
            validationResults.add(testResult);
        }

        int presentFieldCount = validationResults.getCount(true);
        if (presentFieldCount == PRESENT_FIELD_COUNT_MUST_BE) {
            return true;
        }

        if (presentFieldCount < PRESENT_FIELD_COUNT_MUST_BE) {
            context.disableDefaultConstraintViolation();
            String message = UNDER_REQUIRED_ERROR_TEMPLATE.formatted(String.join(", ", fields), mustNot);
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
            return false;
        }

        context.disableDefaultConstraintViolation();
        String message = OVER_REQUIRED_ERROR_TEMPLATE.formatted(String.join(", ", fields), mustNot);
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
        return false;
    }

    private BiPredicate<Object, String> validateFunction() {
        return switch (mustNot) {
            case NULL -> this::isNotNull;
            case EMPTY -> this::isNotEmpty;
            case BLANK -> this::isNotBlank;
        };
    }

    private boolean isNotNull(Object value, String fieldName) {
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(value);
        return beanWrapper.getPropertyValue(fieldName) != null;
    }

    private boolean isNotEmpty(Object value, String fieldName) {
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(value);
        Object propertyValue = beanWrapper.getPropertyValue(fieldName);

        try {
            return CollectionUtils.size(propertyValue) > 0;
        } catch (IllegalArgumentException e) {
            log.error("Field {} is not a Collection type, it should not reached", fieldName);
            return false;
        }
    }

    private boolean isNotBlank(Object value, String fieldName) {
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(value);
        Object propertyValue = beanWrapper.getPropertyValue(fieldName);
        if (Objects.isNull(propertyValue)) {
            return false;
        }

        if (propertyValue instanceof String str) {
            return StringUtils.isNotBlank(str);
        }

        log.error("Field {} is not a String type, it should not reached", fieldName);
        return false;
    }

}