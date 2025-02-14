package com.manish.controllers;

import com.manish.dtos.requests.FieldMustPresentTestPayload;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FieldMustPresentController {

    @PostMapping("/field-must-present-test")
    public FieldMustPresentTestPayload create(@Valid @RequestBody FieldMustPresentTestPayload payload) {
        return payload;
    }

}
