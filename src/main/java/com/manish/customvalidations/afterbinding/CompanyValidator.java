package com.manish.customvalidations.afterbinding;

import com.manish.dtos.requests.CompanyInput;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("CompanyValidator")
public class CompanyValidator implements Validator {

    private final MessageSource messageSource;

    public CompanyValidator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CompanyInput.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CompanyInput companyInput = (CompanyInput) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address",
            "company.address.required");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
                "company.name.required");

        if (StringUtils.isBlank(companyInput.getCustomerCareNumber())) {
            errors.rejectValue("customerCareNumber", "company.customerCareNumber.required",
                    getMessage());
        }
    }

    private String getMessage() {
        return messageSource.getMessage("company.customerCareNumber.required",
                new Object[]{"hello", "world", "to"},"Some Default Message" ,
                LocaleContextHolder.getLocale());
    }
}
