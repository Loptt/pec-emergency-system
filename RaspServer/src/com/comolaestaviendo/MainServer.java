package com.comolaestaviendo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.PrivilegedAction;
import java.util.ArrayList;

public class MainServer extends Thread {

    private final int PORT_NUMBER = 7500;

    private BluetoothServer bluetoothServer;
    private ServerSocket serverSocket;

    private ArrayList<IPConnection> ipConnections;
    private int connectionAmount = 0;

    private boolean btDeviceConnected = false;

    public MainServer() throws IOException {
        bluetoothServer = new BluetoothServer();
        serverSocket    = new ServerSocket(PORT_NUMBER);
        ipConnections   = new ArrayList<>();

        bluetoothServer.start();
    }

    //Starting point of server
    public void initialize() {
        //Initialize servers if necessary

        //Start thread to wait for connections
        this.start();

        //Main loop to handle connections and attend events
        while (true) {
            handleConnections();
            handleBtDevice();
        }

    }

    //Thread override method
    public void run() {

        IPConnection connection;

        try {
            //Loop to wait for new connections
            while (true) {
                Socket socket = serverSocket.accept();
                connection = new IPConnection(socket);

                System.out.println("Connected: " + socket.toString());

                ipConnections.add(connection);
                ipConnections.get(ipConnections.size()-1).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleConnections() {

        ArrayList<Integer> removeIndices = new ArrayList<>();

        //Attend each connection
        for (int i = 0; i < ipConnections.size(); ++i) {
            if (!ipConnections.get(i).getIsConnectionActive()) {
                removeIndices.add(i);
            }

            if (ipConnections.get(i).getInputString() != null) {
                handleCode(ipConnections.get(i));
                ipConnections.get(i).setInputString(null);
            }
        }

        //Remove closed connections
        for (Integer i: removeIndices) {
            ipConnections.remove(i);
        }
    }

    private void handleCode(IPConnection connection) {

        String code  = connection.getInputString();

        switch (code) {
            case "CONNECT-BT-DEVICE":
                //TODO: figure out a way to connect bt device and wait for confirmation

                break;
            case "GET-STATUS":
                if (bluetoothServer.getBtDeviceConnected()) {
                    connection.sendOutput("STATUS-CONNECTED");
                }
                else {
                    connection.sendOutput("STATUS-DISCONNECTED");
                }
                break;
        }
    }

    private void handleCode(String code) {

        switch (code) {
            case "EMERGENCY-ACTIVATED":
                //EMERGENCY HAS BEEN ACTIVATED
                break;
        }
    }

    private void handleBtDevice() {

        if (bluetoothServer.getBtDeviceConnected()) {

            if (bluetoothServer.getIncomingMessage() != null) {
                handleCode(bluetoothServer.getIncomingMessage());
                bluetoothServer.setIncomingMessage(null);
            }
        }
    }
}
