package com.example.http;

import com.example.service.impl.DefaultASyncTokenService;
import com.example.service.impl.SimpleAsyncTokenServiceImpl;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import static java.util.Objects.isNull;

public final class Server {

    private static final Logger LOG = Logger.getLogger(Server.class.getName());

    private static final int POOL_SIZE = 1;
    private static final int HTTP_PORT = 8181;

    private HttpServer httpServer;

    private static Server INSTANCE;

    private Server() {
        try {
            setUp();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setUp() throws IOException {

        httpServer = HttpServer.create(new InetSocketAddress(HTTP_PORT), 0);
        httpServer.createContext("/api/token/issue", new RequestHandler(new SimpleAsyncTokenServiceImpl(new DefaultASyncTokenService())));

        Executor executor = Executors.newFixedThreadPool(POOL_SIZE);

        httpServer.setExecutor(executor);
    }

    public void init() {

        LOG.info("Server Initialized...");
        httpServer.start();
    }

    public static Server getInstance() {

        if (isNull(INSTANCE)) {

            synchronized (Server.class) {

                INSTANCE = new Server();
            }
        }
        return INSTANCE;
    }

}
