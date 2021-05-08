package com.agregating.events.repository;

import com.agregating.events.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JpaRepository implementation for Event POJO
 *
 * @author Dmitri Tšornõi
 */

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
