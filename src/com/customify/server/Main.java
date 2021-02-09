/*
 * By Verite ,Patrick & Samuel
 * Desc: The core server loader
 * */

package com.customify.server;
import com.customify.server.Db.*;
import com.customify.server.utils.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static final int portNumber = 6000;
    public static void main(String[] args) throws Exception {
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
                        System.out.println("Terminating because of " + e.getMessage());
                    }
                    RequestHandler con = new RequestHandler(clientSocket);
                    con.init();
                System.out.println("-- Finished communicating with client --" + clientSocket.getInetAddress().toString());
            }
        } catch (IOException e) {
            System.out.println("Can not listen to port: " + portNumber + ", Exception " + e);
        }
    }
}