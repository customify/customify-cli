
package com.customify.server;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

public class SendToClient {
    private Socket socket;
    private List<String> responseData;
    ObjectOutputStream objectOutput;

    public SendToClient(Socket socket, List<String> responseData) {
        this.socket = socket;
        this.responseData = responseData;
        this.send();
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public List<String> getResponseData() {
        return responseData;
    }

    public void setResponseData(List<String> responseData) {
        this.responseData = responseData;
    }

    public boolean send(){
        try {
            OutputStream output = this.socket.getOutputStream();
            this.objectOutput = new CustomizedObjectOutputStream(output);
            objectOutput.writeObject(this.responseData);
        } catch (Exception e) {
            System.out.println("MESSAGE => "+e.getMessage());
        }
        return false;
    }
}
