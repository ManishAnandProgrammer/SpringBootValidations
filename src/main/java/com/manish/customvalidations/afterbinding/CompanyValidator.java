package com.manish.customvalidations.afterbinding;

import com.manish.dtos.requests.CompanyInput;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("CompanyValidator")
public class CompanyValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CompanyInput.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CompanyInput companyInput = (CompanyInput) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address",
            "company.address.required", "Address Is Mandatory");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
                "company.name.required", "Name Is Mandatory");
    }
}
