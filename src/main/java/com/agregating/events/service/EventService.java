package com.agregating.events.service;

import com.agregating.events.domain.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {

    List<Event> findAllEvents();

    Optional<Event> findEventById(long id);

    Event saveEvent(Event event);

    Event updateEvent(Long id, Event event);

    boolean deleteEvent(Long id);

    void deleteAll();
}
