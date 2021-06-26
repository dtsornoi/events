package com.agregating.events.service.implementation;

import com.agregating.events.domain.Event;
import com.agregating.events.repository.EventRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class EventServiceImplementationTest {
  @Mock EventRepository eventRepository;

  Event event = new Event();

  @BeforeEach
  void createNewEvent() {
    event.setId(UUID.randomUUID());
    event.setTitle("title");
    event.setDescription("description");
  }

  @Test
  @DisplayName("Should return list of events form DB")
  void shouldReturnListOfEventsFromDB() {
    List<Event> events = new ArrayList<>();
    events.add(new Event());
    events.add(new Event());

    Mockito.when(eventRepository.findAll()).thenReturn(events);

    EventServiceImplementation eventServiceImplementation =
        new EventServiceImplementation(eventRepository);

    List<Event> result = eventServiceImplementation.findAllEvents();

    Assertions.assertEquals(events, result);
  }

  @Test
  @DisplayName("Should return Event, when Id is given")
  void shouldReturnEvent_WhenIdIsGiven() {
    Mockito.when(eventRepository.findById(event.getId())).thenReturn(Optional.of(event));

    EventServiceImplementation eventServiceImplementation = new EventServiceImplementation(eventRepository);

    Event result = eventServiceImplementation.findEventById(event.getId()).get();

    Assertions.assertNotNull(result);
    Assertions.assertEquals(event, result);
  }

  @Test
  @DisplayName("Event should be saved to DB")
  void eventShouldBeSavedToDB(){
    Mockito.when(eventRepository.save(event)).thenReturn(event);

    EventServiceImplementation eventServiceImplementation = new EventServiceImplementation(eventRepository);

    Event result = eventServiceImplementation.saveEvent(event);

    Assertions.assertNotNull(result);
    Assertions.assertEquals(event, result);
  }

  @Test
  @DisplayName("Should return updated event, when new one is given")
  void shouldReturnUpdatedEvent_whenNewEventIsGiven(){
    Event newEvent = new Event();
    newEvent.setTitle("titl");
    newEvent.setDescription("Descrpt");
    Mockito.when(eventRepository.save(event)).thenReturn(event);
    Mockito.when(eventRepository.findById(event.getId())).thenReturn(Optional.of(event));

    EventServiceImplementation eventServiceImplementation = new EventServiceImplementation(eventRepository);

    Event result = eventServiceImplementation.updateEvent(event.getId(), newEvent);

    Assertions.assertNotNull(result);
    Assertions.assertEquals(event, result);
  }
}
