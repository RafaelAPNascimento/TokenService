package com.example.model;

import java.util.Objects;

public class UserToken {

    private String token;

    public UserToken() {}

    public UserToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserToken userToken = (UserToken) o;
        return Objects.equals(token, userToken.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }

    @Override
    public String toString() {
        return "UserToken{" +
                "token='" + token + '\'' +
                '}';
    }
}
