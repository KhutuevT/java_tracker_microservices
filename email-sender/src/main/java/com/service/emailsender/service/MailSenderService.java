package com.service.emailsender.service;

import org.backend.tracker.libs.MailParams;

public interface MailSenderService {
    void sendEmail(MailParams mailParams);
}
