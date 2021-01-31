package com.customify.server;
import com.customify.server.Db.Db;
import com.customify.server.utils.ConnectionHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static final int portNumber = 3000;

    public static void main(String[] args) {
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(portNumber);
            System.out.println("New server has been listening on port: " + portNumber);

            for (;;) {
                Socket clientSocket = null;
                try {
                    Db.init();
                    System.out.println("** Listening on port ***");
                    clientSocket = serverSocket.accept();
                    System.out.println("Accepted socket connection from a client with address: " + clientSocket.getInetAddress().toString() + " on a port " + clientSocket.getPort());
                } catch (IOException e) {
                    Db.closeConnection();
                    System.out.println("Terminating because of "+e.getMessage());

                    //e.printStackTrace();
                }

                ConnectionHandler con = new ConnectionHandler(clientSocket);
                con.init();
                System.out.println("-- Finished communicating with client --" + clientSocket.getInetAddress().toString());
            }

        } catch (IOException e) {
            System.out.println("Can not listen to port: " + portNumber + ", Exception " + e);
        }
    }
}

