package com.agregating.events.repository;

import com.agregating.events.domain.ERole;
import com.agregating.events.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * JpaRepository implementation for Role POJO
 *
 * @author Dmitri Tšornõi
 */
@Repository
public interface RoleRepository extends MongoRepository<Role, UUID> {
  Optional<Role> findByName(ERole name);
}
