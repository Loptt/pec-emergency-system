
class PecItProtocol:

    message = ""

    def __init__(self):
        pass

    def process_command(self, command, bt_server):

        self.message = command

        if self.message == "STATUS-GET":
            return bt_server.connected
