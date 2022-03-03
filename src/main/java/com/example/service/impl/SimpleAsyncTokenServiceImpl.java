package com.example.service.impl;

import com.example.model.Credentials;
import com.example.model.UserToken;
import com.example.service.AsyncTokenService;
import com.example.service.SimpleAsyncTokenService;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class SimpleAsyncTokenServiceImpl implements SimpleAsyncTokenService {

    private AsyncTokenService tokenService;

    public SimpleAsyncTokenServiceImpl(AsyncTokenService tokenService) {

        this.tokenService = Objects.requireNonNull(tokenService);
    }

    @Override
    public UserToken requestToken(Credentials credentials) {

        return tokenService.requestToken(credentials);
    }
}
