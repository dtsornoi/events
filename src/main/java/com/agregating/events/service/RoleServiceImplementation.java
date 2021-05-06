package com.agregating.events.service;

import com.agregating.events.domain.ERole;
import com.agregating.events.domain.Role;
import com.agregating.events.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RoleServiceImplementation implements RoleService{

    private RoleRepository repository;

    @Autowired
    public RoleServiceImplementation(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Optional<Role> findByName(ERole name) {
        return repository.findByName(name);
    }
}