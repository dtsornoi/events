package com.agregating.events.service;

import com.agregating.events.domain.Event;
import com.agregating.events.domain.User;

import java.util.List;
import java.util.Optional;

public interface CustomUserService {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findById(long id);

    void saveUser(User user);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
