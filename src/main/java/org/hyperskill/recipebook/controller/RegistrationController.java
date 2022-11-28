package org.hyperskill.recipebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.hyperskill.recipebook.entity.User;
import org.hyperskill.recipebook.service.UserService;

import javax.validation.Valid;

@RestController
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/register")
    public void register(@Valid @RequestBody User user) {
        userService.save(user);
    }
}
