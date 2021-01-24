package customify.server.controllers;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Auth {
    DataOutputStream output;


    public void login(Socket socket)throws IOException{
        output = new DataOutputStream(socket.getOutputStream());
        output.writeUTF("You success fully logged in");
    }

    public void loginError(Socket socket) throws IOException {
        output = new DataOutputStream(socket.getOutputStream());
        output.writeUTF("Login failed");
    }
}
