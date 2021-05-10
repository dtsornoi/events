package com.agregating.events.service;

import com.agregating.events.domain.User;
import com.agregating.events.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomUserServiceImplementation implements CustomUserService {

    private final UserRepository repository;

    @Autowired
    public CustomUserServiceImplementation(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    @Transactional
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    @Transactional
    public Optional<User> findById(long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        return repository.save(user);
    }

    @Override
    @Transactional
    public Boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    @Transactional
    public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
