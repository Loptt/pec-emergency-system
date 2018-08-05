package com.comolaestaviendo;

public class DualServer {

    private BluetoothServer bluetoothServer;
    private InternetServer internetServer;

    private boolean btDeviceConnected;

    public DualServer() {
        bluetoothServer = new BluetoothServer();
        internetServer = new InternetServer();
    }

    public void start() {
        btDeviceConnected = bluetoothServer.initialize();

    }


}
