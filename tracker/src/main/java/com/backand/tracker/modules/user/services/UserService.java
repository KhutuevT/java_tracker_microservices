package com.backand.tracker.modules.user.services;

import com.backand.tracker.modules.user.User;

public interface UserService {
    User getUser(Long id);

    User getUserByUsername(String username);

    User createNewUser(User user);

    boolean existsByUsername(String username);
}
