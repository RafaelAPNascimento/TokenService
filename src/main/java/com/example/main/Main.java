package com.example.main;

import com.example.http.Server;

public class Main {

    public static void main(String[] args) {

        Server server = Server.getInstance();
        server.init();
    }

}
