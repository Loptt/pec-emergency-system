package com.pacosanchez.emergencysystem;

import java.io.IOException;
import java.net.Socket;

public class StatConnection {
    public static  IPConnection connection;


    static {
        try {
            connection = new IPConnection(new Socket());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
