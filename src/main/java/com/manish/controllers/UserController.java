package com.manish.controllers;

import com.manish.dtos.requests.UserUpsertRequest;
import com.manish.dtos.responses.UserResponse;
import com.manish.services.commands.UserCommandService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserCommandService userCommandService;

    public UserController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @PutMapping("/users")
    public UserResponse upsert(@Valid @RequestBody final UserUpsertRequest upsertRequest) {
        return userCommandService.saveOrUpdate(upsertRequest);
    }
}
