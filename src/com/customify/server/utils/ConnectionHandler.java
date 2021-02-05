package com.customify.server.utils;

import com.customify.server.controllers.AuthController;
import com.customify.server.controllers.ProductController;
import com.customify.server.controllers.BusinessController;
import com.customify.shared.Request;
import com.customify.shared.Keys;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.*;

import static com.customify.shared.Keys.LOGIN;

public class ConnectionHandler {
    private final Socket clientSocket;
    private InputStream input;
    private ObjectInputStream objectInput;
    private Request request;

    public ConnectionHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void init() throws Exception{
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

//Method Commented due to that it is already defined below

//    public void handleRequest() throws IOException, SQLException {
//        AuthController authController;
//
//                try{
//                        List<Request> clientRequest = (List<Request>) this.objectInput.readObject();
//                        this.request = clientRequest.get(0);
//                        this.handleRequest();
//                    }
//                catch(Exception e)
//                {
//                }
//                catch (IOException e) {
//                System.out.println("Error in reading Object " + e.getMessage());
//                }
//    }

    public void handleRequest() throws Exception {
      AuthController authController;
        BusinessController businessController;
        switch (request.getKey()) {
            case LOGIN:
                authController = new AuthController(this.clientSocket, this.request);
                authController.login();
                break;
            case REGISTER:
                authController = new AuthController(this.clientSocket, this.request);
                authController.signup();
                  authController = new AuthController(this.clientSocket,this.request);
                  authController.signup();
            case CREATE_BUSINESS:
                businessController = new BusinessController(this.clientSocket, this.request);
                businessController.create();
                break;
            case CREATE_PRODUCT:
                ProductController productController = new ProductController(this.clientSocket, this.request);
                productController.registerProduct();
            case GET_BUSINESS:
                businessController = new BusinessController(this.clientSocket,this.request);
                businessController.getall();
                break;
            case REMOVE_BUSS:
                businessController = new BusinessController(this.clientSocket,this.request);
                businessController.deleteBusiness();
                break;
            case GET_BUSS_BYID:
                businessController = new BusinessController(this.clientSocket,this.request);
                businessController.getById();
                break;
            default:
                System.out.println("\t\t\tSORRY INVALID API KEY");
        }
    }
}