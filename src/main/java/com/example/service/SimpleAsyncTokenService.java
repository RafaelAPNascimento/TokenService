package com.example.service;

import com.example.model.Credentials;
import com.example.model.UserToken;

public interface SimpleAsyncTokenService {

    UserToken requestToken(Credentials credentials);
}
