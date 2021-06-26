package com.agregating.events.service.implementation;

import com.agregating.events.domain.User;
import com.agregating.events.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ForgotPasswordService {

  private CustomUserService userService;

  @Autowired
  public ForgotPasswordService(CustomUserService userService) {
    this.userService = userService;
  }

  public Boolean verifyEmail(String email) {
    User userFromDB =
        userService
            .findByEmail(email.toLowerCase().trim())
            .orElseThrow(() -> new RuntimeException("User not found"));
    System.out.println("hello");
    return userFromDB.getEmail().equals(email);
  }

  public String resetPassword(String mail) {
    User user =
        userService.findByEmail(mail).orElseThrow(() -> new RuntimeException("User not found"));
    String newPassword = UUID.randomUUID().toString();
    user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
    userService.saveUser(user);
    return newPassword;
  }
}
