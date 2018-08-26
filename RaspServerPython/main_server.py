import socket
import threading
import bluetooth_server as bt
import internet_server as it
import pec_it_protocol as pec


class MainServer:

    bt_server = bt.BluetoothServer()
    it_server = it.InternetServer()
    pec_protocol = pec.PecItProtocol()

    bt_server_thread = threading.Thread()

    def __init__(self):
        print("Initializing main server...")

        it_server_thread = threading.Thread(target=self.it_server.run)
        it_server_thread.daemon = True
        print("Switching internet server to thread...")
        it_server_thread.start()

        bt_server_thread = threading.Thread(target=self.bt_server.run)
        bt_server_thread.daemon = True

    def handle_it_command(self, command):
        print("Recieved " + command + " from Mobile client")

        first_three_command = command[:3]

        if first_three_command == "PEC":
            response = self.pec_protocol.process_command(command)
            self.it_server.send_message(response)

        elif first_three_command == "BLT":

            if command == "BLT-CONNECT":
                self.bt_server.connect_bt()
                bt_server_thread.start()

        else:
            print("Command: " + command + " not recognized.")

    def handle_bt_command(self, command):
        print("Recieved " + command + " from BT client")

        first_three_command = command[:3]

        if first_three_command == "BLT":

            if command == "BLT-EMERGENCY":
                # Send emergency
                pass

    def run(self):

        print("Starting...")

        while True:

            if self.it_server.command != "":
                self.handle_command(self.it_server.command)
                self.it_server.command = ""

            if self.bt_server.data != "":
                self.handle_bt_command(self.bt_server.data)
