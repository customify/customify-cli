package com.customify.server.utils;

import com.customify.server.controllers.AuthController;
import com.customify.server.controllers.ProductController;
import com.customify.shared.Request;
import com.customify.shared.Keys;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.*;

public class ConnectionHandler {
    private final Socket clientSocket;
    private InputStream input;
    private ObjectInputStream objectInput;
    private Request request;

    public ConnectionHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void init() {
        try {
            this.input = this.clientSocket.getInputStream();
            this.objectInput = new ObjectInputStream(input);
            while (true) {
                try {
                    List<Request> clientRequest = (List<Request>) this.objectInput.readObject();
                    this.request = clientRequest.get(0);
                    this.handleRequest();
                } catch (IOException | ClassNotFoundException | SQLException e) {
                }
            }
        } catch (IOException e) {
            System.out.println("Error in reading Object " + e.getMessage());
        }
    }


    public void handleRequest() throws IOException, SQLException {
        AuthController authController;
        ProductController productController = new ProductController(this.clientSocket, this.request);

        switch (request.getKey()) {
            case LOGIN:
                authController = new AuthController(this.clientSocket, this.request);
                authController.login();
                break;
            case REGISTER:
                authController = new AuthController(this.clientSocket, this.request);
                authController.signup();
                break;
            case CREATE_PRODUCT:
                productController.registerProduct();

            case GET_ALL_PRODUCTS:
                productController.getAllProducts();
            default:
                System.out.println("\t\t\tSORRY INVALID API KEY");
        }
    }
}