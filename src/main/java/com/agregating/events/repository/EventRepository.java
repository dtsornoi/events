package com.agregating.events.repository;

import com.agregating.events.domain.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * JpaRepository implementation for Event POJO
 *
 * @author Dmitri Tšornõi
 */
@Repository
public interface EventRepository extends MongoRepository<Event, UUID> {}
