package com.service.emailsender.controller;

import com.service.emailsender.service.MailSenderService;
import org.backend.tracker.libs.MailParams;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/email")
@RestController
public class MailSenderControllerV1 {
    private final MailSenderService mailSenderService;

    public MailSenderControllerV1(MailSenderService mailSenderService) {
        this.mailSenderService = mailSenderService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody MailParams mailParams) {
        mailSenderService.sendEmail(mailParams);
        return ResponseEntity.ok().build();
    }
}
