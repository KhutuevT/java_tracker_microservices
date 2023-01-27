package org.backend.emailservice.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class MailParams implements Serializable {
    @JsonProperty("text")
    private String text;

    @JsonProperty("emailTo")
    private String emailTo;

    @JsonProperty("subject")
    private String subject;

    @Deprecated
    public MailParams(){}

    public MailParams(
            String text,
            String emailTo,
            String subject
    ) {
        this.text = text;
        this.emailTo = emailTo;
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
