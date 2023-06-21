package com.eden.backendcore.controller;

import com.eden.backendcore.service.MailService;
import com.eden.backendcore.viewmodel.SendMailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping
    public ResponseEntity<Object> sendMail(@RequestBody SendMailRequest request) {
        return ResponseEntity.ok(mailService.sendEmail(request));
    }
}
