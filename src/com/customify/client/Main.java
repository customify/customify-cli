package com.customify.client;

<<<<<<< HEAD
import com.customify.client.views.Home;

=======
>>>>>>> 281b09713be9b603f3875586d05f5db6d47a276a
import java.io.*;
import java.net.*;

public class Main {
    private OutputStream output = null;
    private ObjectOutputStream objectOutput = null;
    private boolean isConnectionOn = true;

    public Main(String serverIP){
        if(!connectTOServer(serverIP)){
            System.out.println("Failed to connect to the server to: "+serverIP);
        }
    }
    public static void main(String[] args){
        new Main("localhost");
        System.out.println("Shutting down................");
    }
    private boolean connectTOServer(String serverIp){
        int portNumber = 3000;
        try{
            Socket socket = new Socket(serverIp, portNumber);
            while(isConnectionOn){
                new Home(socket).view();
//                Login log = new Login(socket);
//                log.view();
            }
        }catch (Exception e){
            this.isConnectionOn = false;
            System.out.println("Failed to connect to the server at port: "+ portNumber);
            System.out.println("Exception: "+ e.toString());
        }
        return true;
    }
}