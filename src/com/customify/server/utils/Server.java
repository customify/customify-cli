package com.customify.server.utils;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class Server extends Thread{

    final Socket clientSocket;

    public Server(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
       while (true){
           RequestHandler con = new RequestHandler(clientSocket);
           while(true) {
               try {
                   con.init(clientSocket.getInputStream());
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       }
    }
}