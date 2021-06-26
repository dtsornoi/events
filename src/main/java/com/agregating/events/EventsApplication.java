package com.agregating.events;

import com.agregating.events.domain.ERole;
import com.agregating.events.domain.Role;
import com.agregating.events.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class EventsApplication implements CommandLineRunner {

  @Autowired private RoleRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(EventsApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    repository.deleteAll();
    repository.save(new Role(UUID.randomUUID(), ERole.ROLE_USER));
    repository.save(new Role(UUID.randomUUID(), ERole.ROLE_ORGANIZER));
  }
}
