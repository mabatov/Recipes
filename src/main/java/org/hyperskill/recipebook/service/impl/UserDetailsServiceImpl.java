package org.hyperskill.recipebook.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.hyperskill.recipebook.entity.User;
import org.hyperskill.recipebook.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmailIgnoreCase(email).orElseThrow(
                () -> new UsernameNotFoundException("Not found " + email)
        );

        return new UserDetailsImpl(user);
    }
}
