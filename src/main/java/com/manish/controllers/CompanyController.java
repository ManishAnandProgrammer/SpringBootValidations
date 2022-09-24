package com.manish.controllers;

import com.manish.customvalidations.afterbinding.CompanyValidator;
import com.manish.dtos.requests.CompanyInput;
import lombok.SneakyThrows;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

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
        BindException bindException =
                new BindException(companyInput, "company");

        ValidationUtils.invokeValidator(companyValidator, companyInput, bindException);
        if (bindException.hasErrors())
            throw new BindException(bindException);
    }

}
