package com.eden.backendcore.service.impl;

import com.eden.backendcore.service.MailService;
import com.eden.backendcore.viewmodel.SendMailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;

    @Override
    public Object sendEmail(SendMailRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(request.getEmail());
        message.setTo("nvhien2703@outlook.com");
        message.setSubject("Inquiry from %s(%s): %s".formatted(
                request.getSender(), request.getEmail(), request.getSubject()));
        message.setText(request.getContent());

        javaMailSender.send(message);
        return "Sent successfully.";
    }
}
