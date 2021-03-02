package com.customify.server.utils;

import com.customify.server.services.*;
import com.customify.server.Keys;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;


public class RequestHandler {

    private final Socket clientSocket;
    private Keys key;
    private String json_data;

    public RequestHandler(Socket socket) {
        this.clientSocket = socket;
    }


    public void init(InputStream inputStream) throws IOException, ClassNotFoundException, SQLException {

        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        List<String> clientRequest = (List) objectInputStream.readObject();
        this.json_data = (String) clientRequest.get(0);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json_data);
        this.key = Keys.valueOf(jsonNode.get("key").asText());
        this.handleRequest();
    }


    public void handleRequest() throws IOException, SQLException {
//        CustomerService  customer = new CustomerService(this.clientSocket);
        BusinessService businessService = new BusinessService(this.clientSocket);
//        ProductService productService = new ProductService(this.clientSocket);
        PointsService pointsService = new PointsService(this.clientSocket);
        CouponService couponService = new CouponService(this.clientSocket);


        System.out.println("Handling routes");

        switch (this.key) {
            case CREATE_BUSINESS:
                businessService.create(json_data);
                break;
            case EDIT_BUSINESS:
                businessService.update(json_data);
                break;
            case REMOVE_BUSINESS:
                businessService.removeBusiness(json_data);
            case CREATE_PRODUCT:
//                productController.registerProduct();
                break;
            case FEEDBACK:
//                FeedbackController fController = new FeedbackController(this.clientSocket, this.request);
//                fController.sendDataInDb();
                break;
            case GET_ALL_PRODUCTS:
//                productController.getAllProducts();
                break;
            case DELETE_PRODUCT:
//                productController.deleteProduct();
                break;

            case GET_PRODUCT_BY_ID:
//                productService.getProductById(json_data);
                break;

            case UPDATE_PRODUCT:
//                productService.updateProduct(json_data);
                break;
            case CREATE_CUSTOMER:
//                customer.create();
                break;
            case GET_ALL_BUSINESSES:
                businessService.getAll();
                break;
            case GET_BUSINESS:
                businessService.getBusinessById(json_data);
                break;
            case GET_WINNERS:
                pointsService.getWinners();
                break;
            case AUTHENTICATION:
                AuthService auth = new AuthService(this.clientSocket,this.json_data);
                break;
            case DISABLE_CUSTOMER:
//                customer.disable();
                break;
            case CREATE_COUPON:
                couponService.coupingByProduct(json_data);
                break;
            case GET_ALL_COUPONS:
                couponService.getAllCoupons(json_data);
            default:
                System.out.println("\t\t\tSORRY INVALID API KEY");
        }
    }

}