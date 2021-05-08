package com.agregating.events.service;

import com.agregating.events.domain.CustomUserDetails;
import com.agregating.events.domain.User;
import com.agregating.events.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * implementation of UserDetailsService
 * for getting user from Repository by username
 * and returning CustomUserDetails
 *
 * @author Dmitri Tšornõi
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User  " + username + " not found"));

        return new CustomUserDetails(user);
    }
}
