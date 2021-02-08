package com.customify.client;


import com.customify.client.views.*;

import java.io.*;
import java.net.*;

public class Main {
    private OutputStream output = null;
    private ObjectOutputStream objectOutput = null;
    private boolean isConnectionOn = true;

    public Main(String serverIP){
        System.out.println(serverIP);
        if(!connectTOServer(serverIP)){
            System.out.println("Failed to connect to the server to: "+serverIP);
        }
    }

    public static void main(String[] args){
        System.out.println("Customify  is starting..........");
        new Main("localhost");
        System.out.println("Ending................");
    }

    private boolean connectTOServer(String serverIp){
<<<<<<< HEAD
        int portNumber = 3000;
=======
        int portNumber = 6000;
>>>>>>> c3de07e96f442335f7d4ef31318a970ac01c850c
        try{
            Socket socket = new Socket(serverIp, portNumber);
            System.out.println("Connected to the server "+ socket.getInetAddress() + " on port "+ socket.getPort());
            System.out.println("from local Address: "+ socket.getLocalAddress()+" and port "+ socket.getLocalPort());

            while(isConnectionOn){
                Home home = new Home(socket);
                home.view();
            }

        }catch (Exception e){
            this.isConnectionOn = false;
            System.out.println("Failed to connect to the server at port: "+ portNumber);
            System.out.println("Exception: "+ e.toString());
        }
        return true;
    }
}