package com.agregating.events.repository;

import com.agregating.events.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * JpaRepository implementation for User POJO
 *
 * @author Dmitri Tšornõi
 */

@Repository
public interface UserRepository extends MongoRepository<User, UUID> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
