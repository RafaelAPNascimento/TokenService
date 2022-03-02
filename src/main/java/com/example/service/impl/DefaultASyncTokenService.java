package com.example.service.impl;

import com.example.exception.BusinessException;
import com.example.model.Credentials;
import com.example.model.User;
import com.example.model.UserToken;
import com.example.service.AsyncTokenService;

import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.Objects.requireNonNull;

public class DefaultASyncTokenService implements AsyncTokenService {

    @Override
    public UserToken requestToken(Credentials credentials) {

        User user = authenticate(credentials);
        UserToken token = issueToken(user);
        return token;
    }

    @Override
    public User authenticate(Credentials credentials) {

        sleep();

        requireNonNull(credentials, "Null credentials.");
        requireNonNull(credentials.getUsername(), "Null Username");
        requireNonNull(credentials.getPassword(), "Null password");

        if (credentials.getPassword()
                        .equals(credentials.getUsername().toUpperCase())) {

            User user = new User(credentials.getUsername());
            return user;
        }

        throw new BusinessException("Invalid Credentials");
    }

    @Override
    public UserToken issueToken(User user) {

        sleep();

        validateUser(user);

        String token = String.format("%s_%s", user.getUserId(), Instant.now());
        UserToken userToken = new UserToken(token);

        return userToken;
    }

    private void validateUser(User user) {

        requireNonNull(user, "Null user.");
        requireNonNull(user.getUserId(), "Null id.");

        if (user.getUserId().startsWith("A"))
            throw new BusinessException("User starts with A");
    }

    private static void sleep() {

        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1, 5000));
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
