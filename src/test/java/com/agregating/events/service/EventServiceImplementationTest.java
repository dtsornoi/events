package com.agregating.events.service;

import com.agregating.events.domain.Event;
import com.agregating.events.domain.User;
import com.agregating.events.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for EventServiceImplementation
 *
 * @author Dmitri Tšornõi
 */

@ExtendWith(MockitoExtension.class)
class EventServiceImplementationTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventServiceImplementation eventService;

    private Event event;

    private User user;

    private User anotherUser;

    @BeforeEach
    void init(){
        event = new Event();
        event.setId(1L);
        event.setTitle("event");
        event.setDescription("event description");

        user = new User();
        user.setId(1L);
        user.setUsername("user");

        anotherUser = new User();
        anotherUser.setId(2L);
        anotherUser.setUsername("user2");
    }

    @Test
    @DisplayName("When findAllEvents() called, should return List of events")
    void whenFindAllEventsCalled_shouldReturnListOfEvents() {
        List<Event> events = new ArrayList<>();
        events.add(event);

        Mockito.when(eventService.findAllEvents()).thenReturn(events);

        List<Event> result = eventService.findAllEvents();

        assertEquals(events, result);
        assertEquals(events.toArray().length, result.toArray().length);
    }

    @Test
    @DisplayName("When findEventById() called, should return event with requested id")
    void whenFindEventById_shouldReturnOptionalOfEvent() {
        Mockito.when(eventService.findEventById(1L)).thenReturn(Optional.of(event));

        Event result = eventService.findEventById(1L)
                .orElseThrow(() -> new RuntimeException("Event does not exist"));

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(event, result);
        assertEquals(event.getId(), result.getId());
    }

    @Test
    @DisplayName("When called saveEvent(), should return saved Event")
    void saveEvent() {
        Mockito.when(eventService.saveEvent(event)).thenReturn(event);

        Event result = eventService.saveEvent(event);

        assertNotNull(result);
        assertEquals(event, result);
    }

    @Test
    @DisplayName("When updateEvent() called, should return updated Event")
    void whenUpdateEventCalled_shouldReturnUpdatedEvent() {
        Event newEvent = new Event();
        newEvent.setId(1L);
        newEvent.setTitle("New Event");
        newEvent.setDescription("event description");

        Mockito.when(eventService.findEventById(1L)).thenReturn(Optional.of(event));
        Mockito.when(eventService.updateEvent(1L, newEvent)).thenReturn(newEvent);

        Event result = eventService.updateEvent(1L, newEvent);

        assertEquals(event, result);
        assertNotNull(result);
    }

    @Test
    @DisplayName("When addSubscriber() called, should return Event with list of subscribers ")
    void whenAddSubscriberCalled_shouldReturnEventWithSubscribersList() {
        List<User> users = new ArrayList<>();
        users.add(user);
        event.setSubscribedUsers(users);

        Mockito.when(eventService.findEventById(1L)).thenReturn(Optional.of(event));
        Mockito.when(eventService.addSubscriber(1L, user)).thenReturn(event);

        Event result = eventService.addSubscriber(1L, user);

        assertEquals(event, result);
    }

    @Test
    @DisplayName("When deleteSubscriberCalled(), should return Event with deleted subscriber")
    void whenDeleteSubscriberCalled_shouldReturnEventWithDeletedSubscriber() {
        List<User> users = new ArrayList<>();
        users.add(anotherUser);
        event.setSubscribedUsers(users);

        Mockito.when(eventService.findEventById(1L)).thenReturn(Optional.of(event));
        Mockito.when(eventService.deleteSubscriber(1L, anotherUser)).thenReturn(event);

        Event result = eventService.deleteSubscriber(1L, anotherUser);

        assertEquals(event, result);
    }
}