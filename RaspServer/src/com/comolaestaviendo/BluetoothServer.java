package com.comolaestaviendo;
import javax.bluetooth.*;
import java.io.IOException;

public class BluetoothServer {

    public BluetoothServer() {

    }

    public void run() throws IOException {

        LocalDevice device = LocalDevice.getLocalDevice();

        RemoteDevice[] remotedevice = device.getDiscoveryAgent().retrieveDevices(DiscoveryAgent.PREKNOWN);

        for(RemoteDevice d : remotedevice)
        {
            System.out.println("Device Name : "+d.getFriendlyName(false));
            System.out.println("Bluetooth Address : "+d.getBluetoothAddress()+"\n");
        }
    }
}
