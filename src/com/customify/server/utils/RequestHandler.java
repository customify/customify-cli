package com.customify.server.utils;

import com.customify.server.services.CouponService;
import com.customify.server.Keys;
import com.customify.server.controllers.AuthController;
import com.customify.server.controllers.FeedbackController;
import com.customify.server.services.BusinessService;
import com.customify.shared.Request;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;


public class RequestHandler {

    private final Socket clientSocket;
    private InputStream input;
    private ObjectInputStream objectInput;
    private Keys key;
    private String json_data;

    public RequestHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void init() {
        try {
            this.input = this.clientSocket.getInputStream();
            this.objectInput = new ObjectInputStream(this.input);

            while(true) {
                while(true) {
                    try {
                        List<String> clientRequest = (List)this.objectInput.readObject();
                        this.json_data = (String)clientRequest.get(0);
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(json_data);
                        System.out.println(jsonNode);
                        this.handleRequest();
                    } catch (Exception var5) {
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error in reading Object " + e.getMessage());
        }
    }

    public void handleRequest() throws IOException, SQLException {
        BusinessService businessService = new BusinessService(this.clientSocket);
        CouponService couponService = new CouponService(this.clientSocket);

        switch (this.key) {
            case LOGIN:
            case REGISTER:
            case CREATE_BUSINESS:
                businessService.create(json_data);
                break;
            case EDIT_BUSINESS:
                businessService.update(json_data);
                break;
            case CREATE_PRODUCT:
            case FEEDBACK:
            case GET_ALL_PRODUCTS:
            case DELETE_PRODUCT:
            case CREATE_CUSTOMER:
                System.out.println("CUSTOMER RECORDS RECEIVED "+json_data);
                break;
            case CREATE_COUPON:
                System.out.println("json formatted data"+json_data);
                couponService.coupingByProduct(json_data);
                break;
            default:
                System.out.println("\t\t\tSORRY INVALID API KEY");
        }
    }

}
