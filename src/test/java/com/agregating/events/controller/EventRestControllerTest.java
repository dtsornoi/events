package com.agregating.events.controller;

import com.agregating.events.domain.Event;
import com.agregating.events.domain.User;
import com.agregating.events.service.EventService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class EventRestControllerTest {

    @Mock
    private EventService service;

    @InjectMocks
    private EventRestController controller;

    private Event event;

    @BeforeEach
    void init(){
        event = new Event();
        event.setId(1L);
        event.setTitle("Title");
    }

    @Test
    @DisplayName("When getAllEvents is called, should return list of events and status code 200")
    void whenGetAllEventsCalled_shouldReturnListOfEventsAndStatusCode200() {


        List<Event> events = new ArrayList<>();
        events.add(event);

        Mockito.when(service.findAllEvents()).thenReturn(events);

        ResponseEntity<List<Event>> result = controller.getAllEvents();

        Assertions.assertThat(result.getBody()).isEqualTo(events);
        Assertions.assertThat(result.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    @DisplayName("When findEventById called, should return Event with Id and status code 200")
    void whenFindEventByIdCalled_shouldReturnEventWithIdAndStatusCode200() {
        Mockito.when(service.findEventById(1L)).thenReturn(Optional.of(event));

        ResponseEntity<Event> result = controller.findEventById(1L);

        Assertions.assertThat(result.getBody().getId()).isEqualTo(event.getId());
        Assertions.assertThat(result.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void saveEvent() {
    }

    @Test
    void updateEvent() {
    }

    @Test
    void subscribeUserToEvent() {
    }

    @Test
    void deleteSubscriber() {
    }
}