package com.example.http;

import com.example.exception.BusinessException;
import com.example.model.Credentials;
import com.example.model.UserToken;
import com.example.service.SimpleAsyncTokenService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Objects;
import java.util.logging.Logger;

public class RequestHandler implements HttpHandler {

    private static Logger LOG = Logger.getLogger(RequestHandler.class.getName());

    private SimpleAsyncTokenService tokenService;

    public RequestHandler(SimpleAsyncTokenService tokenService) {

        this.tokenService = Objects.requireNonNull(tokenService);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String method = exchange.getRequestMethod();

        try {
            if ("POST".equals(method))
                handlePost(exchange);
        }
        catch (NullPointerException | BusinessException e) {

            handleError(exchange, e.getMessage(), 400);
        }
        catch (Exception e) {

            e.printStackTrace();
            handleError(exchange, e.getMessage(), 500);
        }
    }

    private void handlePost(HttpExchange exchange) throws IOException {

        try (OutputStreamWriter writer = new OutputStreamWriter(exchange.getResponseBody())) {

            InputStreamReader payload = new InputStreamReader(exchange.getRequestBody());

            Credentials credentials = new Gson().fromJson(payload, Credentials.class);
            UserToken userToken = tokenService.requestToken(credentials);

            String userTokeJson = new Gson().toJson(userToken);

            writer.write(userTokeJson);
            exchange.sendResponseHeaders(200, userTokeJson.getBytes().length);
        }
    }

    private void handleError(HttpExchange exchange, String message, int statusResponse) throws IOException {

        try (OutputStreamWriter writer = new OutputStreamWriter(exchange.getResponseBody())) {

            writer.write(message);
            exchange.sendResponseHeaders(statusResponse, message.getBytes().length);
        }
    }
}
