package com.backand.tracker.modules.auth.services;

import com.backand.tracker.modules.user.User;
import com.backand.tracker.exceptions.UserAlreadyExistException;
import com.backand.tracker.security.jwt.JwtTokenProvider;
import com.backand.tracker.modules.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthServiceImpl(
            UserService userService,
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String authorization(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        User user = userService.getUserByUsername(username);

        return jwtTokenProvider.createToken(username);
    }

    @Override
    public void registration(User user) {
        if (userService.existsByUsername(user.getUsername())) {
            throw  new UserAlreadyExistException("User already exist!");
        }

        userService.createNewUser(user);
    }
}
