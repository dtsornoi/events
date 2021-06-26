package com.agregating.events.controller;

import com.agregating.events.domain.User;
import com.agregating.events.payload.response.ResponseUser;
import com.agregating.events.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

/**
 * Rest Controller for Users
 *
 * @author Dmitri Tšornõi
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserRestController {

  private final CustomUserService service;

  @Autowired
  public UserRestController(CustomUserService service) {
    this.service = service;
  }

  /**
   * GET: <code>/id</code>
   *
   * @param id of requested user
   * @return User.class stored in DB requested from client side
   */
  @GetMapping("/{id}")
  public ResponseEntity<ResponseUser> findById(@PathVariable("id") UUID id) {
    Optional<User> optionalUser = service.findById(id);
    ResponseUser responseUser = new ResponseUser();
    User user = null;

    if (optionalUser.isPresent()) {
      user = optionalUser.get();
      responseUser.setId(user.getId());
      responseUser.setUsername(user.getUsername());
      responseUser.setEmail(user.getEmail());
      responseUser.setRoles(user.getRoles());

      return new ResponseEntity<>(responseUser, HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
