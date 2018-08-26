package com.pacosanchez.emergencysystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;


public class IPConnection extends Thread {

    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;
    private boolean emergency = false;

    private String inputString, outputString;

    private boolean isConnectionActive = true;

    public IPConnection(Socket socket) throws IOException {
        this.socket = socket;
        output = new PrintWriter(socket.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        inputString = "empty";
    }

    @Override
    public void run() {

        try {
            while (true) {
                inputString = input.readLine();
                emergency = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        closeConnection();
    }

    public void sendOutput(String code) {
        output.println(code);
    }

    public String getInputString() {
        return inputString;
    }

    public void setInputString(String newString) {
        inputString = newString;
    }

    public boolean getIsConnectionActive() {
        return isConnectionActive;
    }

    public void closeConnection() {
        output.close();

        try {
            input.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        isConnectionActive = false;

    }

    public boolean isEmergency() {
        return emergency;
    }
}