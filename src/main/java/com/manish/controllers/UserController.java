package com.manish.controllers;

import com.manish.domains.User;
import com.manish.dtos.requests.PasswordInput;
import com.manish.dtos.requests.UserUpsertRequest;
import com.manish.dtos.responses.UserResponse;
import com.manish.services.commands.UserCommandService;
import com.manish.services.queries.UserQueryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UserController {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public UserController(UserCommandService userCommandService,
                          UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    @PutMapping("/users")
    public UserResponse upsert(@Valid @RequestBody final UserUpsertRequest upsertRequest) {
        return userCommandService.saveOrUpdate(upsertRequest);
    }

    @PatchMapping("/users/{id}/password")
    public UserResponse setPassword(@PathVariable("id") @NotBlank(message = "{required.id}") Long id,
                                    @Valid @RequestBody final PasswordInput passwordInput) {
        User user = userQueryService.findById(id);
        user.setPassword(passwordInput.getPassword());
        return userCommandService.saveOrUpdate(user);
    }
}
