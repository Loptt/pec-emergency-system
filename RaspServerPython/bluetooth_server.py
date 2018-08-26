from bluetooth import *
import subprocess


class BluetoothServer:

    address = "98:D3:31:FB:60:EF", 1
    socket = BluetoothSocket(RFCOMM)

    connected = False

    def __init__(self):
        pass

    def connect_bt(self):
        try:
            self.socket.connect(address)
            self.connected = True
            print("BT device connected")
        except:
            print("Failed connection")

    def run(self):

        while True:
            try:
                self.data = self.bt.read()

                if not self.data
                    connected = False

            except:
                print("Bluetooth device disconnected !!!")
                self.connected = False
