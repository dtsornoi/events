package com.agregating.events.controller;

import com.agregating.events.domain.User;
import com.agregating.events.service.EventsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserRestController {

    private EventsUserService service;

    @Autowired
    public UserRestController(EventsUserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findByUsername(@PathVariable("id") long id){
        Optional<User> optionalUser = service.findById(id);

        return optionalUser
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
}
