package com.service.emailsender.service.impl;

import com.service.emailsender.service.MailSenderService;
import org.backend.tracker.libs.MailParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderServiceImpl implements MailSenderService {
    @Value("${spring.mail.username}")
    private String emailFrom;
    private final JavaMailSender javaMailSender;

    public MailSenderServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(MailParams mailParams) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(emailFrom);
        mailMessage.setText(mailParams.getText());
        mailMessage.setTo(mailParams.getEmailTo());
        mailMessage.setSubject(mailParams.getSubject());
    }
}
