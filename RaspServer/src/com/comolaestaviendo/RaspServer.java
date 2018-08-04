package com.comolaestaviendo;

import java.io.IOException;

public class RaspServer {

    public static void main(String args[]) {

        BluetoothServer bluetoothServer = new BluetoothServer();

        bluetoothServer.run();

    }
}
