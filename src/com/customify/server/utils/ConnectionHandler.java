package com.customify.server.utils;


import com.customify.server.controllers.*;
import com.customify.server.services.PointsController;
import com.customify.shared.Request;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

public class ConnectionHandler {
    private final Socket clientSocket;
    private InputStream input;
    private ObjectInputStream objectInput;
    private Request request;

    public ConnectionHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void init() throws Exception {
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

    // Method Commented due to that it is already defined below

    // public void handleRequest() throws IOException, SQLException {
    // AuthController authController;
    //
    // try{
    // List<Request> clientRequest = (List<Request>) this.objectInput.readObject();
    // this.request = clientRequest.get(0);
    // this.handleRequest();
    // }
    // catch(Exception e)
    // {
    // }
    // catch (IOException e) {
    // System.out.println("Error in reading Object " + e.getMessage());
    // }
    // }

    public void handleRequest() throws IOException, SQLException {
        AuthController authController;
        ProductController productController = new ProductController(this.clientSocket, this.request);
        PointsController pointsController = new PointsController(this.clientSocket,this.request);

        switch (request.getKey()) {
            case LOGIN:
                authController = new AuthController(this.clientSocket, this.request);
                authController.login();
                break;
            case REGISTER:
                authController = new AuthController(this.clientSocket, this.request);
                authController.signup();
                
            case CREATE_BUSINESS:
//                businessController = new BusinessController(this.clientSocket, this.request);
//                businessController.create();
                break;
            case GET_WINNERS:
                pointsController.getWinners();
                break;
//            case POINTS_BY_CUSTOMER_EMAIL:
//                pointsController.getPointsByCustomerEmail();
            case CREATE_PRODUCT:
                productController.registerProduct();

            case FEEDBACK:
                FeedbackController fController = new FeedbackController(this.clientSocket, this.request);
                fController.sendDataInDb();

                break;
            case GET_ALL_PRODUCTS:
                productController.getAllProducts();
                break;
            case DELETE_PRODUCT:
                productController.deleteProduct();
                break;
            default:
                System.out.println("\t\t\tSORRY INVALID API KEY");
        }
    }
}