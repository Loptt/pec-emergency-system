package com.comolaestaviendo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MainServer {

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
    }

    public void start() throws IOException {
        //Initialize servers if necessary

        IPConnection connection;

        while (true) {
            Socket socket = serverSocket.accept();
            connection = new IPConnection(socket);
            ipConnections.add(connection);
            ipConnections.get(ipConnections.size()-1).run();

            handleConnections();
        }

    }

    private void handleConnections() {

        ArrayList<Integer> removeIndices = new ArrayList<>();

        for (int i = 0; i < ipConnections.size(); ++i) {
            if (!ipConnections.get(i).getIsConnectionActive()) {
                removeIndices.add(i);
            }
        }

        //Remove closed connections
        for (Integer i: removeIndices) {
            ipConnections.remove(i);
        }
    }
}
