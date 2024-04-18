package com.cineverse.cineversebackend.user.email.service;

import com.cineverse.cineversebackend.user.email.dto.EmailMessage;
import com.cineverse.cineversebackend.user.member.service.MemberService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final MemberService memberService;

    @Value("${spring.mail.username}")
    private String from;

    public EmailMessage sendMail(EmailMessage emailMessage) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

            String newPwd = createRandom();
            mimeMessageHelper.setTo(emailMessage.getTo());
            mimeMessageHelper.setSubject("비밀번호 재설정");
            mimeMessageHelper.setText("재설정된 비밀번호: " + newPwd);
            mimeMessageHelper.setFrom(new InternetAddress(from));

            javaMailSender.send(mimeMessage);
            memberService.changePwd(newPwd, emailMessage.getTo());

            emailMessage.setMessage("재설정된 비밀번호: " + newPwd);
            emailMessage.setSubject("비밀번호 재설정");

            return emailMessage;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String createRandom() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }

        return result.toString();
    }
}
