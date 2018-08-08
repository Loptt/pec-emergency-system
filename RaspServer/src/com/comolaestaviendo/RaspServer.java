package com.comolaestaviendo;

import java.io.IOException;

public class RaspServer {

    public static void main(String args[]) throws IOException {
        MainServer server = new MainServer();
        server.initialize();
    }
}
