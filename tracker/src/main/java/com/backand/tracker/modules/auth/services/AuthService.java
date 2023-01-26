package com.backand.tracker.modules.auth.services;

import com.backand.tracker.modules.user.User;

public interface AuthService {
    String authorization(String username, String password);

    void registration(User user);
}
