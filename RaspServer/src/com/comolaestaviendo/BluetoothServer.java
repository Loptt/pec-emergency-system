package com.comolaestaviendo;
import javax.bluetooth.*;

class BluetoothServer {

    private LocalDevice localDevice;

    BluetoothServer() {

        try {
            localDevice = LocalDevice.getLocalDevice();
        } catch (BluetoothStateException e) {
            e.printStackTrace();
        }
    }

    void run() {

        System.out.println("Local device name: " + localDevice.getFriendlyName());
        System.out.println("Local device address: " + localDevice.getBluetoothAddress());
    }
}
