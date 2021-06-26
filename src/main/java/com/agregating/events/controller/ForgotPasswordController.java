package com.agregating.events.controller;

import com.agregating.events.service.implementation.EmailService;
import com.agregating.events.service.implementation.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/forgot-password")
public class ForgotPasswordController {

  private EmailService emailService;
  private ForgotPasswordService forgotPasswordService;

  @Autowired
  public ForgotPasswordController(
      EmailService emailService, ForgotPasswordService forgotPasswordService) {
    this.emailService = emailService;
    this.forgotPasswordService = forgotPasswordService;
  }

  @PostMapping("/{mail}")
  public ResponseEntity<HttpStatus> sendVerificationEmail(@PathVariable("mail") String mail) {

    if (forgotPasswordService.verifyEmail(mail)) {
      emailService.sendMessage(mail, "Forgot Password", forgotPasswordService.resetPassword(mail));
      return new ResponseEntity<>(HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }
}
