package com.agregating.events.controller;

import com.agregating.events.service.implementation.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/forgot-password")
public class ForgotPasswordController {

    private EmailService emailService;

    @Autowired
    public ForgotPasswordController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/")
    public ResponseEntity<HttpStatus> sendVerificationEmail(@RequestBody String mail){
        if (emailService.verifyEmail(mail)){
            emailService.sendMessage(mail, "Forgot Password", "Password reset link");
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
