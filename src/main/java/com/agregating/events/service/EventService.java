package com.agregating.events.service;

import com.agregating.events.domain.Event;
import com.agregating.events.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * interface for Event Service
 *
 * @author Dmitri Tšornõi
 */

public interface EventService {

    List<Event> findAllEvents();

    Optional<Event> findEventById(UUID id);

    Event saveEvent(Event event);

    Event updateEvent(UUID id, Event event);

    boolean deleteEvent(UUID id);

    void deleteAll();

    Event addSubscriber(UUID id ,User user);

    Event deleteSubscriber(UUID id, User user);
}
