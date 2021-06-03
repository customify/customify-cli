  
/*
 *
 * By Verite ,  Patrick & Samuel
 * Desc: The core server loader
 *
 * */

package com.customify.server;


import com.customify.server.Db.*;
import com.customify.server.utils.*;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final int portNumber = 3000;


    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(portNumber);
            System.out.println("New server has been listening on port: " + portNumber);
            Db.init();
            while(true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("New Client is connected on the Servers");
                Thread client = new Server(clientSocket);
                ExecutorService Pool = Executors.newFixedThreadPool(2);
                Pool.execute(client);
                //client.start();
            }
        } catch (Exception e) {
            System.out.println("Can not listen to port: " + portNumber + ", Exception " + e);

        }
    }
}
