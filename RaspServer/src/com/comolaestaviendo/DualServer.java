package com.comolaestaviendo;

public class DualServer {

    private BluetoothServer bluetoothServer;
    private InternetServer internetServer;

    private boolean btDeviceConnected;

    public DualServer() {
        bluetoothServer = new BluetoothServer();
        internetServer  = new InternetServer();
    }

    public void start() {
        btDeviceConnected = bluetoothServer.initialize();

        if (!btDeviceConnected) {
            System.out.println("WARNING: Emergency bracelet not connected");
        }

        //TODO: Initialize internet server on new thread

    }


}
