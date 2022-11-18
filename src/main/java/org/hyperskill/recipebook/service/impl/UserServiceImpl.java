package org.hyperskill.recipebook.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.hyperskill.recipebook.entity.User;
import org.hyperskill.recipebook.repository.UserRepository;
import org.hyperskill.recipebook.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;

    private final PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @Override
    public void save(User user) {
        if (userRepo.findByEmailIgnoreCase(user.getEmail()).isEmpty()) {
            //user.setEmail(user.getEmail());
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRole("ROLE_USER");
            userRepo.save(user);
        } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
}
