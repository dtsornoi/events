package com.agregating.events.service;

import com.agregating.events.domain.Event;
import com.agregating.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImplementation implements EventService{

    private EventRepository repository;

    @Autowired
    public EventServiceImplementation(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public List<Event> findAllEvents() {
        return repository.findAll();
    }

    @Override
    public Optional<Event> findEventById(long id) {
        return Optional.empty();
    }

    @Override
    public Event saveEvent(Event event) {
        return null;
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        return null;
    }

    @Override
    public boolean deleteEvent(Event event) {
        return false;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }
}
