package com.comolaestaviendo;

import java.io.IOException;

public class RaspServer {

    public static void main(String args[]) {

        BluetoothServer bluetoothServer = new BluetoothServer();

        try {
            bluetoothServer.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
