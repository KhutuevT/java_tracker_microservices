package com.backand.tracker.modules.user.primitives;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Password {

    private String password;

    public Password(){}

    public Password(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.password = bCryptPasswordEncoder.encode(password);
    }

    @Override
    public String toString() {
        return "********";
    }
}
