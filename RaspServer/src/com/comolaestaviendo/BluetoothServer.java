package com.comolaestaviendo;

import javax.bluetooth.*;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import java.io.*;

public class BluetoothServer {

    private LocalDevice localDevice;

    private static final String serviceName = "PECEmergencySystemService";
    private static final String serviceStringUUID = "3dfcd8aa13e342878b89e60f31d0801b";

    private UUID serviceUUID;
    private String connectionURL;
    private StreamConnectionNotifier connectionNotifier;
    private RemoteDevice remoteDevice;

    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    private String incomingMessage;

    private boolean btDeviceConnected = false;

    public BluetoothServer() {

        serviceUUID = new UUID(serviceStringUUID, false);
        connectionURL = "btspp://localhost:" + serviceUUID.toString() + ";name=" + serviceName;

        try {
            connectionNotifier = (StreamConnectionNotifier) Connector.open(connectionURL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            localDevice = LocalDevice.getLocalDevice();
            localDevice.setDiscoverable(DiscoveryAgent.GIAC);
        } catch (BluetoothStateException e) {
            e.printStackTrace();
        }
    }

    private void getInput() throws IOException {

        while (!(incomingMessage.isEmpty())) {
            incomingMessage = inputStream.readUTF();
        }
    }

    public void sendOutput(String message) throws IOException {
        outputStream.flush();
        outputStream.writeChars(message);
    }

    private void connect() {
        StreamConnection connection;

        try {
            connection = connectionNotifier.acceptAndOpen();
            remoteDevice = RemoteDevice.getRemoteDevice(connection);

            System.out.println("Client connected: " + remoteDevice.getFriendlyName(false));
            inputStream = connection.openDataInputStream();
            outputStream = connection.openDataOutputStream();

            btDeviceConnected = true;

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getIncomingMessage() {
        return incomingMessage;
    }

    public boolean getBtDeviceConnected() {
        return btDeviceConnected;
    }
}
