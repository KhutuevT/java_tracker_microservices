package com.backand.tracker.security.jwt;

import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(@Lazy UserService userService) {
        this.userService = userService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        boolean userExist = userService.existsByUsername(username);
        if (!userExist) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        User user = userService.getUserByUsername(username);
        JwtUser jwtUser = JwtUserFactory.create(user);
        return jwtUser;
    }
}
