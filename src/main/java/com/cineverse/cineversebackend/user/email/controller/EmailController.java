package com.cineverse.cineversebackend.user.email.controller;

import com.cineverse.cineversebackend.user.email.dto.EmailMessage;
import com.cineverse.cineversebackend.user.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<EmailMessage> sendMail(@RequestBody EmailMessage emailMessage) {
        emailService.sendMail(emailMessage);
        return new ResponseEntity<>(emailMessage, HttpStatus.OK);
    }
}
