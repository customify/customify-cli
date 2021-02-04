package com.customify.server;

import java.io.*;
import java.net.*;
import java.util.*;

import com.customify.client.views.CustomerFeedbackView;

/**
 * This thread handles connection for each connected commenter, so the server
 * can handle multiple commenters at the same time.
 */
public class CustomerThread extends Thread {
    private Socket socket;
    private CustomerFeedbackServer server;
    private CustomerFeedbackView feedView;
    private PrintWriter writer;

    public CustomerThread(Socket socket, CustomerFeedbackServer server) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);

            PrintCommenters();

            String commenter = reader.readLine();
            server.addCommenter(commenter);

            String serverMessage = "New commenter connected: " + commenter;
            server.broadcast(serverMessage, this);

            String commenterMessage;

            do {
                commenterMessage = reader.readLine();                
                serverMessage = "[" + commenter + "]:  " + "{" + "Customer_id: " + feedView.getCustomerId()
                        + "\nBusiness_id: " + feedView.getBusinessId() + "\nTitle: " + feedView.getTitle()
                        + "\nDescription: " + feedView.getDescription() + "}";
                server.broadcast(serverMessage, this);

            } while (!commenterMessage.equals("bye")); // and check if the customer_id and business_id are present

            server.removeUser(commenter, this);
            socket.close();

            serverMessage = commenter + " has quitted.";
            server.broadcast(serverMessage, this);

        } catch (IOException ex) {
            System.out.println("Error in CustomerThread: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Sends a list of online commenters to the newly connected user.
     */
    void PrintCommenters() {
        if (server.hasCommenters()) {
            writer.println("Connected commenters: " + server.getCommenters());
        } else {
            writer.println("No other users connected");
        }
    }

    /**
     * Sends a message to the commenter.
     */
    void sendMessage(String message) {
        writer.println(message);
    }
}