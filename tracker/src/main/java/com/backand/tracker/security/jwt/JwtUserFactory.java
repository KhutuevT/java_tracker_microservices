package com.backand.tracker.security.jwt;

import com.backand.tracker.modules.user.User;

public class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getEmailAddress(),
                user.getPassword()
        );
    }
}
