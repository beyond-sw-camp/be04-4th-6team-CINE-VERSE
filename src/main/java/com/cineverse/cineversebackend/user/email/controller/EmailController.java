package com.cineverse.cineversebackend.user.email.controller;

import com.cineverse.cineversebackend.user.email.dto.EmailMessage;
import com.cineverse.cineversebackend.user.email.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "이메일 발송 요청", description = "이메일을 발송 합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<EmailMessage> sendMail(
            @Parameter(required = true, description = "이메일 발송 요청")
            @RequestBody EmailMessage emailMessage) {
        emailService.sendMail(emailMessage);
        return new ResponseEntity<>(emailMessage, HttpStatus.OK);
    }
}
