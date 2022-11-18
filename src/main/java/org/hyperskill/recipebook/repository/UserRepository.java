package org.hyperskill.recipebook.repository;

import org.springframework.data.repository.CrudRepository;
import org.hyperskill.recipebook.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmailIgnoreCase(String email);
}
