package org.hyperskill.recipebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.hyperskill.recipebook.entity.User;
import org.hyperskill.recipebook.repository.UserRepository;
import org.hyperskill.recipebook.service.UserService;

import javax.validation.Valid;

@RestController
public class RegistrationController {

/*    @Autowired
    UserRepository userRepo;
    @Autowired
    PasswordEncoder encoder;


    // TODO check if @Valid used correctly here
    @PostMapping("/api/register")
    public void register(@Valid @RequestBody User user) {
        if (userRepo.findByEmailIgnoreCase(user.getEmail()) == null) {
            user.setRole("ROLE_USER");
            user.setPassword(encoder.encode(user.getPassword()));
            userRepo.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }*/

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    //БЕЗ АВТОРИЗАЦИИ(ТОЛЬКО ТЕЛО ЗАПРОСА)
    @PostMapping("/api/register")
    public void register(@Valid @RequestBody User user) {
        userService.save(user);
    }
}
