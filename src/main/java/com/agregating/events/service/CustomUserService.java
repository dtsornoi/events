package com.agregating.events.service;

import com.agregating.events.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface CustomUserService {
  Optional<User> findByUsername(String username);

  Optional<User> findByEmail(String email);

  Optional<User> findById(UUID id);

  User saveUser(User user);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
