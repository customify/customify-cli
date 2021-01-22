package com.customify;


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
                    System.out.println("** Listening on port ***");
                    clientSocket = serverSocket.accept();
                    System.out.println("Accepted socket connection from a client with address: " + clientSocket.getInetAddress().toString() + " on a port " + clientSocket.getPort());
                } catch (IOException e) {
                    System.out.println("Terminating because of "+e.getMessage());
                    //e.printStackTrace();
                }

                ConnectionHandler con = new ConnectionHandler(clientSocket);
                con.init();
                System.out.println("-- Finished communicating with client --" + clientSocket.getInetAddress().toString());
            }
            /*
            * try {
                System.out.println("Closing the server gracefully");
                serverSocket.close();
            } catch (IOException e) {
                System.out.println("Could no close the server " + e.getMessage());
            }
            * */
        } catch (IOException e) {
            System.out.println("Can not listen to port: " + portNumber + ", Exception " + e);
        }
    }
}
