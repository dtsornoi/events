package com.agregating.events.service;

import com.agregating.events.domain.ERole;
import com.agregating.events.domain.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(ERole name);
}
