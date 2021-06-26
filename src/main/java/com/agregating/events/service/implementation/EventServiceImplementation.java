package com.agregating.events.service.implementation;

import com.agregating.events.domain.Event;
import com.agregating.events.domain.User;
import com.agregating.events.repository.EventRepository;
import com.agregating.events.repository.UserRepository;
import com.agregating.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * implementation on Event Service
 *
 * @author Dmitri Tšornõi
 */
@Service
public class EventServiceImplementation implements EventService {

  private final EventRepository repository;
  private final UserRepository userRepository;

  @Autowired
  public EventServiceImplementation(EventRepository repository, UserRepository userRepository) {
    this.repository = repository;
    this.userRepository = userRepository;
  }

  @Override
  @Transactional
  public List<Event> findAllEvents() {

    return repository.findAll();
  }

  @Override
  @Transactional
  public Optional<Event> findEventById(UUID id) {
    return repository.findById(id);
  }

  @Override
  @Transactional
  public Event saveEvent(Event event) {
    event.setSubscribedUsers(new ArrayList<>());
    return repository.save(event);
  }

  @Override
  @Transactional
  public Event updateEvent(UUID id, Event newEvent) {
    Optional<Event> oldEvent = findEventById(id);

    if (oldEvent.isPresent()) {
      Event tempEvent = oldEvent.get();
      tempEvent.setDescription(newEvent.getDescription());
      tempEvent.setTitle(newEvent.getTitle());
      tempEvent.setStartingFrom(newEvent.getStartingFrom());
      tempEvent.setEndingOn(newEvent.getEndingOn());
      tempEvent.setUser(newEvent.getUser());
      tempEvent.setSubscribedUsers(newEvent.getSubscribedUsers());

      return repository.save(tempEvent);
    } else {
      throw new RuntimeException("No such repository");
    }
  }

  @Override
  @Transactional
  public boolean deleteEvent(UUID id) {
    Optional<Event> eventOptional = findEventById(id);

    if (eventOptional.isPresent()) {
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
  public Event addSubscriber(UUID id, User user) {
    Event event = findEventById(id).orElseThrow(() -> new RuntimeException("Event not found"));
    List<User> users = event.getSubscribedUsers();
    users.add(user);
    event.setSubscribedUsers(users);

    return updateEvent(id, event);
  }

  @Override
  public Event deleteSubscriber(UUID id, User user) {
    Event event = findEventById(id).orElseThrow(() -> new RuntimeException("Event not found"));
    List<User> users = event.getSubscribedUsers();
    users.remove(user);
    event.setSubscribedUsers(users);
    return updateEvent(id, event);
  }
}
