package com.customify.server;

import java.io.*;
import java.net.*;
import java.util.*;

public class CustomerFeedbackServer {
    private int port;
    private Set<String> commenters = new HashSet<>();
    private Set<CustomerThread> CustomerThreads = new HashSet<>();

    public CustomerFeedbackServer(int port) {
        this.port = port;
    }

    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Feedback Server is listening on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New commenter connected");

                CustomerThread newCommenter = new CustomerThread(socket, this);
                CustomerThreads.add(newCommenter);
                newCommenter.start();

            }

        } catch (IOException ex) {
            System.out.println("Error in the server: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("Syntax: java ChatServer <port-number>");
            System.exit(0);
        }

        int port = Integer.parseInt(args[0]);
        CustomerFeedbackServer server = new CustomerFeedbackServer(port);
        server.execute();
    }

    /**
     * Delivers a message from one user to others (broadcasting)
     */
    void broadcast(String message, CustomerThread excludeUser) {
        for (CustomerThread aUser : CustomerThreads) {
            if (aUser != excludeUser) {
                aUser.sendMessage(message);
            }
        }
    }

    // Send the message to the specified person



    /**
     * Stores username of the newly connected client.
     */
    void addCommenter(String commenter) {
        commenters.add(commenter);
    }

    /**
     * When a commenter is disconneted, removes the associated commenter names and
     * CustomerThread
     */
    void removeUser(String userName, CustomerThread aUser) {
        boolean removed = commenters.remove(userName);
        if (removed) {
            CustomerThreads.remove(aUser);
            System.out.println("The user " + userName + " quitted");
        }
    }

    Set<String> getCommenters() {
        return this.commenters;
    }

    /**
     * Returns true if there are other users connected (not count the currently
     * connected user)
     */
    boolean hasCommenters() {
        return !this.commenters.isEmpty();
    }
}