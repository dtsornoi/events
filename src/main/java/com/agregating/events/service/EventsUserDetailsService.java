package com.agregating.events.service;

import com.agregating.events.domain.EventsUserDetails;
import com.agregating.events.domain.User;
import com.agregating.events.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventsUserDetailsService implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public EventsUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = repository.findByUsername(username);

        if (!userOptional.isPresent()){
            throw new UsernameNotFoundException("username not found");
        }

        User user = userOptional.get();

        return new EventsUserDetails(user);
    }
}
