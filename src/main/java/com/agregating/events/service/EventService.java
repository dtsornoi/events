package com.agregating.events.service;

import com.agregating.events.domain.Event;
import com.agregating.events.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * interface for Event Service
 *
 * @author Dmitri Tšornõi
 */

public interface EventService {

    List<Event> findAllEvents();

    Optional<Event> findEventById(long id);

    Event saveEvent(Event event);

    Event updateEvent(Long id, Event event);

    boolean deleteEvent(Long id);

    void deleteAll();

    Event addSubscriber(long id ,User user);

    Event deleteSubscriber(long id, User user);
}
