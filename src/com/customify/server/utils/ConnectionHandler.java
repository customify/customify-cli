/*
 *
 * By Verite &  Patrick
 * Desc: Dealing with Server connection configurations
 * */

package com.customify.server.utils;

import com.customify.server.controllers.ProductController;
//import com.customify.server.controllers.BusinessController;
import com.customify.server.controllers.PointsController;
import com.customify.server.controllers.FeedbackController;
import com.customify.shared.Request;

import com.customify.shared.Keys;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.*;

import static com.customify.shared.Keys.GET_PRODUT_BY_ID;
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

    public void handleRequest() throws IOException, SQLException {
        ProductController productController;

        PointsController pointsController = new PointsController(this.clientSocket,this.request);

        switch (request.getKey()) {
            case LOGIN:
                break;
            case REGISTER:
                break;
            case CREATE_BUSINESS:
                break;
            case GET_WINNERS:
                pointsController.getWinners();
                break;
            case POINTS_BY_CUSTOMER_EMAIL:
                pointsController.getPointsByCustomerEmail();
            case CREATE_PRODUCT:
                productController = new ProductController(this.clientSocket, this.request);
                productController.registerProduct();

                break;
            case GET_PRODUT_BY_ID:
                productController = new ProductController(this.clientSocket, this.request);
                productController.getProductById();
            case GET_BUSINESS:
                break;
            case FEEDBACK:
                // FeedbackController fController = new FeedbackController(this.clientSocket, this.request);
                // fController.sendDataInDb();

                break;
            case GET_ALL_PRODUCTS:
                productController = new ProductController(this.clientSocket, this.request);
                productController.getAllProducts();
                break;
            case UPDATE_PRODUCT:
                productController = new ProductController(this.clientSocket, this.request);
                productController.updateProduct();
                break;
            default:
                System.out.println("\t\t\tSORRY INVALID API KEY");
        }
    }
}