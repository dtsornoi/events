package com.agregating.events.service.implementation;

import com.agregating.events.domain.ERole;
import com.agregating.events.domain.Role;
import com.agregating.events.repository.RoleRepository;
import com.agregating.events.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * implementation of Role Service
 *
 * @author Dmitri Tšornõi
 */
@Service
public class RoleServiceImplementation implements RoleService {

  private final RoleRepository repository;

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
