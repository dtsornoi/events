package com.agregating.events.controller;

import com.agregating.events.domain.Event;
import com.agregating.events.domain.User;
import com.agregating.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/events")
public class EventRestController {

    private EventService service;

    @Autowired
    public EventRestController(EventService service) {
        this.service = service;
    }

    @GetMapping("/all-events")
    public ResponseEntity<List<Event>> getAllEvents(){
        List<Event> events = service.findAllEvents();

        if (events.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<Event> findEventById(@PathVariable("id") long id){
        Optional<Event> eventOptional = service.findEventById(id);

        return eventOptional
                .map(event -> new ResponseEntity<>(event, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save-event")
    @PreAuthorize("hasRole('ORGANIZER')")
    public ResponseEntity<Event> saveEvent(@RequestBody Event event){

        List<Event> events = service.findAllEvents();
         for (Event currEvent : events){
                if (currEvent.equals(event)){
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        }

        Event upcomingEvent = service.saveEvent(event);
        return new ResponseEntity<>(upcomingEvent, HttpStatus.CREATED);
    }

    @PutMapping("/event/{id}")
    @PreAuthorize("hasRole('ORGANIZER')")
    public ResponseEntity<Event> updateEvent(@PathVariable("id") long id,
                                             @RequestBody Event event){
        Event newEvent = service.updateEvent(id, event);
        if (newEvent == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newEvent, HttpStatus.OK);
    }

    @DeleteMapping("/event/{id}")
    @PreAuthorize("hasRole('ORGANIZER')")
    public ResponseEntity<HttpStatus> deleteEvent(@PathVariable("id") long id){
        boolean success = service.deleteEvent(id);
        if (success){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/subscribe/{id}")
    public ResponseEntity<Event> subscribeUserToEvent(@PathVariable("id") long id,
                                                     @RequestBody User user){
        Event event = service.addSubscriber(id, user);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PostMapping("/unsubscribe/{id}")
    public ResponseEntity<HttpStatus> deleteSubscriber(@PathVariable("id") long id,
                                                  @RequestBody User user){
        service.deleteSubscriber(id, user);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
