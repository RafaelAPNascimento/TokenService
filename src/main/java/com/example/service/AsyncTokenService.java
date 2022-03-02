package com.example.service;

import com.example.model.Credentials;
import com.example.model.User;
import com.example.model.UserToken;

public interface AsyncTokenService {

    User authenticate(Credentials credentials);

    UserToken issueToken(User user);

    UserToken requestToken(Credentials credentials);


}
