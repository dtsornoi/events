package com.agregating.events.service;

import com.agregating.events.domain.Event;
import com.agregating.events.domain.User;
import com.agregating.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * implementation on Event Service
 *
 * @author Dmitri Tšornõi
 */

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
    @Transactional
    public Optional<Event> findEventById(long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Event saveEvent(Event event) {
        return repository.save(event);
    }

    @Override
    @Transactional
    public Event updateEvent(Long id, Event newEvent) {
        Optional<Event> oldEvent = findEventById(id);

        if (oldEvent.isPresent()){
            Event tempEvent = oldEvent.get();
            tempEvent.setDescription(newEvent.getDescription());
            tempEvent.setTitle(newEvent.getTitle());
            tempEvent.setStartingFrom(newEvent.getStartingFrom());
            tempEvent.setEndingOn(newEvent.getEndingOn());
            tempEvent.setUser(newEvent.getUser());

            return repository.save(tempEvent);
        }else {
            throw new RuntimeException("No such repository");
        }
    }

    @Override
    @Transactional
    public boolean deleteEvent(Long id) {
        Optional<Event> eventOptional = findEventById(id);

        if (eventOptional.isPresent()){
            repository.delete(eventOptional.get());
            return true;
        }

        return false;
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Event addSubscriber(long id, User user) {
        Event event = findEventById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        List<User> users = event.getSubscribedUsers();
        users.add(user);
        event.setSubscribedUsers(users);

        return saveEvent(event);
    }

    @Override
    public Event deleteSubscriber(long id, User user) {
        Event event = findEventById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        List<User> users = event.getSubscribedUsers();
        users.remove(user);
        event.setSubscribedUsers(users);
        return saveEvent(event);
    }
}
