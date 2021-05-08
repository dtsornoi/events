package com.agregating.events.service;

import com.agregating.events.domain.User;

import java.util.Optional;

public interface CustomUserService {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findById(long id);

    User saveUser(User user);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
