package com.customify.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SendToServer {
    private OutputStream output = null;
    private ObjectOutputStream objectOutput = null;
    private boolean isConnectionOn = true;
    InputStream input;
    ObjectInputStream objectInputStream;
    private String json;
    private Socket socket;

    public Socket getSocket() {
        return this.socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public SendToServer(String json, Socket socket) throws IOException {
        this.socket = socket;
        this.json = json;
    }

    // NEW VERSION OF COMMON CLASS
    public boolean send() throws IOException {
        try {
            List<String> dataToSend = new ArrayList();
            dataToSend.add(this.json);

            this.output = this.socket.getOutputStream();
            // System.out.println(output.available());
            this.objectOutput = new ObjectOutputStream(this.output);
            // System.out.println(this.output.available);
            this.objectOutput.writeObject(dataToSend);
            // this.objectOutput.flush();
            // this.objectOutput.reset();
            // this.output.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}
