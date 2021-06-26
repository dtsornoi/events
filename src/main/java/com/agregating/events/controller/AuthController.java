package com.agregating.events.controller;

import com.agregating.events.domain.CustomUserDetails;
import com.agregating.events.domain.ERole;
import com.agregating.events.domain.Role;
import com.agregating.events.domain.User;
import com.agregating.events.payload.request.LoginRequest;
import com.agregating.events.payload.request.SignupRequest;
import com.agregating.events.payload.response.JwtResponse;
import com.agregating.events.payload.response.MessageResponse;
import com.agregating.events.security.jwt.JwtUtils;
import com.agregating.events.service.CustomUserService;
import com.agregating.events.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Controller for AuthN and Registration
 *
 * @author Dmitri Tšornõi
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;

  private final CustomUserService userService;
  private final RoleService roleService;

  private final PasswordEncoder encoder;

  private final JwtUtils jwtUtils;

  private final int ORGANIZER = 1;

  @Autowired
  public AuthController(
      AuthenticationManager authenticationManager,
      CustomUserService service,
      PasswordEncoder encoder,
      JwtUtils jwtUtils,
      RoleService roleService) {
    this.authenticationManager = authenticationManager;
    this.userService = service;
    this.encoder = encoder;
    this.jwtUtils = jwtUtils;
    this.roleService = roleService;
  }

  /**
   * Post: <code>/signin</code>
   *
   * @param loginRequest payload from clientside
   * @return jwtResponse with token and user
   */
  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

    List<String> roles =
        userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

    JwtResponse jwtResponse =
        new JwtResponse(
            jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);

    System.out.println(jwtResponse);
    return ResponseEntity.ok(jwtResponse);
  }

  /**
   * Post: <code>/signup</code>
   *
   * @param signupRequest from clientside form
   * @return response message if registration was successful
   */
  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
    int isOrganizer = signupRequest.getIsOrganizer();

    if (userService.existsByUsername(signupRequest.getUsername())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: Username already exists."));
    }

    if (userService.existsByEmail(signupRequest.getEmail())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    User user = new User();
    user.setId(UUID.randomUUID());
    user.setUsername(signupRequest.getUsername());
    user.setPassword(encoder.encode(signupRequest.getPassword()));
    user.setEmail(signupRequest.getEmail());

    List<Role> roles = new ArrayList<>();

    if (isOrganizer == ORGANIZER) {
      roles.add(organizerRole());
    } else {
      roles.add(userRole());
    }

    user.setRoles(roles);
    userService.saveUser(user);

    return ResponseEntity.ok(new MessageResponse("user registered successfully"));
  }

  private Role userRole() {
    return roleService
        .findByName(ERole.ROLE_USER)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
  }

  private Role organizerRole() {
    return roleService
        .findByName(ERole.ROLE_ORGANIZER)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
  }
}
