package com.comolaestaviendo;

import javax.bluetooth.*;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import java.io.*;

public class BluetoothServer extends Thread {

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

    //Thread method
    public void run() {
        try {
            getInput();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Device disconnected");
        }
    }

    public boolean initialize() {
        return connect();
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

    private boolean connect() {
        StreamConnection connection;

        try {
            connection = connectionNotifier.acceptAndOpen();
            remoteDevice = RemoteDevice.getRemoteDevice(connection);

            System.out.println("Client connected: " + remoteDevice.getFriendlyName(false));
            inputStream = connection.openDataInputStream();
            outputStream = connection.openDataOutputStream();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public String getIncomingMessage() {
        return incomingMessage;
    }
}
