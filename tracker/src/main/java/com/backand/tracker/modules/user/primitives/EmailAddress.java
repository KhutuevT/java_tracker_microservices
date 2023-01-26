package com.backand.tracker.modules.user.primitives;

import com.backand.tracker.exceptions.EmailValidationFailException;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.persistence.Embeddable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
@Data
public class EmailAddress {

    private String emailAddress;

    public EmailAddress(){}

    public EmailAddress(String emailAddress ) throws EmailValidationFailException{
        EmailValidator emailValidator = new EmailValidator();
        emailValidator.validate(emailAddress);
        this.emailAddress = emailAddress;
    }

    @Override
    public boolean equals(Object obj) {
        EmailAddress emailAddress2 = (EmailAddress) obj;
        return emailAddress.equalsIgnoreCase(emailAddress2.getEmailAddress());
    }
}

@Service
class EmailValidator {
    private final Pattern pattern;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public void validate(final String hex) throws EmailValidationFailException {
        Matcher matcher = pattern.matcher(hex);
        if (!matcher.matches()){
            throw new EmailValidationFailException();
        }
    }
}