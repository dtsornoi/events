package com.agregating.events.controller;

import com.agregating.events.domain.Event;
import com.agregating.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventRestController {

    private EventService service;

    @Autowired
    public EventRestController(EventService service) {
        this.service = service;
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents(){
        List<Event> events = service.findAllEvents();

        if (events.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(events, HttpStatus.OK);
    }
}
