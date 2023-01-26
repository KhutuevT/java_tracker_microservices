package com.service.emailsender.listener;

import com.service.emailsender.service.MailSenderService;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class MailSenderListener {

    private final MailSenderService mailSenderService;

    public MailSenderListener(MailSenderService mailSenderService) {
        this.mailSenderService = mailSenderService;
    }

    @RabbitListener(queues = "emailQueue")
    public void email(String message) {
        System.out.println(message);
    }
}
