package com.agregating.events.repository;

import com.agregating.events.domain.ERole;
import com.agregating.events.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * JpaRepository implementation for Role POJO
 *
 * @author Dmitri Tšornõi
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
