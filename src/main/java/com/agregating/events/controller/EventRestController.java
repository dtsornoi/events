package com.agregating.events.controller;

import com.agregating.events.domain.Event;
import com.agregating.events.domain.User;
import com.agregating.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Rest Controller for Events
 *
 * @author Dmitri Tšornõi
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/events")
public class EventRestController {

    private final EventService service;

    @Autowired
    public EventRestController(EventService service) {
        this.service = service;
    }

    /**
     * GET: <code>/all-events</code>
     * @return List of all events stored in DB
     */
    @GetMapping("/all-events")
    public ResponseEntity<List<Event>> getAllEvents(){
        List<Event> events = service.findAllEvents();

        if (events.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    /**
     * GET: <code>/event/id</code>
     * @param id of the event to be retrieved from DB
     * @return Event requested from client side
     */
    @GetMapping("/event/{id}")
    public ResponseEntity<Event> findEventById(@PathVariable("id") long id){
        Optional<Event> eventOptional = service.findEventById(id);

        return eventOptional
                .map(event -> new ResponseEntity<>(event, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * POST: <code>/save-event</code>
     * @param event from client side to be persisted to DB
     * @return response and persisted event
     */
    @PostMapping("/save-event")
    @PreAuthorize("hasRole('ORGANIZER')")
    public ResponseEntity<Event> saveEvent(@Valid @RequestBody Event event){

        List<Event> events = service.findAllEvents();
         for (Event currEvent : events){
                if (currEvent.equals(event)){
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        }

        Event upcomingEvent = service.saveEvent(event);
        return new ResponseEntity<>(upcomingEvent, HttpStatus.CREATED);
    }

    /**
     * PUT: <code>/event/id</code>
     * @param id of the old Event stored in DB
     * @param event body of the event to be updated in the old event
     * @return updated old event
     */
    @PutMapping("/event/{id}")
    @PreAuthorize("hasRole('ORGANIZER')")
    public ResponseEntity<Event> updateEvent(@PathVariable("id") long id,
                                            @Valid @RequestBody Event event){
        Event newEvent = service.updateEvent(id, event);
        if (newEvent == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newEvent, HttpStatus.OK);
    }

    /**
     * DELETE: <code>/event/id</code>
     * @param id of the event to be deleted from DB
     * @return response if event was deleted
     */
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

    /**
     * POST: <code>/subscribe/id</code>
     * @param id of the current selected event from client side
     * @param user current user that wishes to be subscribed to event
     * @return event with subscribed users in it
     */
    @PostMapping("/subscribe/{id}")
    public ResponseEntity<Event> subscribeUserToEvent(@PathVariable("id") long id,
                                                     @RequestBody User user){
        Event event = service.addSubscriber(id, user);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    /**
     * POST: <code>/unsubscribe/id</code>
     * @param id of the current selected event on client side
     * @param user to be unsubscribed from the event
     * @return event with list of users subscribed to event
     */
    @PostMapping("/unsubscribe/{id}")
    public ResponseEntity<HttpStatus> deleteSubscriber(@PathVariable("id") long id,
                                                  @RequestBody User user){
        service.deleteSubscriber(id, user);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
