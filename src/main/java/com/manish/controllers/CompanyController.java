package com.manish.controllers;

import com.manish.customvalidations.afterbinding.CompanyValidator;
import com.manish.dtos.requests.CompanyInput;
import lombok.SneakyThrows;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CompanyController {

    private final CompanyValidator companyValidator;

    public CompanyController(CompanyValidator companyValidator) {
        this.companyValidator = companyValidator;
    }

    @PostMapping("/company")
    @SneakyThrows
    public void testCompanyValidator(@RequestBody CompanyInput companyInput) {
        BeanPropertyBindingResult beanPropertyBindingResult =
                new BeanPropertyBindingResult(companyInput, "company");
        ValidationUtils.invokeValidator(companyValidator, companyInput, beanPropertyBindingResult);
        if (beanPropertyBindingResult.hasErrors())
            throw new BindException(beanPropertyBindingResult);
    }
}
