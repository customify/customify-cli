package com.customify.server.utils;

import com.customify.server.services.billing.FeatureService;
import com.customify.server.services.*;
import com.customify.server.*;
import com.customify.server.services.AuthService;
import com.customify.server.services.BusinessService;
import com.customify.server.services.CustomerFeedbackService;
import com.customify.server.Keys;

//import com.customify.server.services.ProductService;
import com.customify.server.services.SalesService;
import com.customify.server.services.BusinessService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.customify.server.services.CouponService;

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

    public void init(InputStream inputStream) throws Exception {

        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        List<String> clientRequest = (List) objectInputStream.readObject();
        System.out.println("MY request "+clientRequest.get(0));
        this.json_data = (String) clientRequest.get(0);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json_data);
        this.key = Keys.valueOf(jsonNode.get("key").asText());
        this.handleRequest();
    }

    public void handleRequest() throws Exception {
        CustomerService customer = new CustomerService(this.clientSocket,json_data);
        EmployeeService employee = new EmployeeService(this.clientSocket);
        BusinessService businessService = new BusinessService(this.clientSocket);
//        ProductService productService = new ProductService(this.clientSocket);
        PointsService pointsService = new PointsService(this.clientSocket);
        ProductService productService = new ProductService(this.clientSocket);
        CouponService couponService = new CouponService(this.clientSocket);
        FeatureService featureService = new FeatureService(this.clientSocket);
        System.out.println("Handling routes "+this.key);
        SalesService salesService = new SalesService(this.clientSocket);
//        CustomerFeedbackService feedback = new CustomerFeedbackService(this.clientSocket);

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
                productService.registerProduct(json_data);
                break;
            case FEEDBACK:
                CustomerFeedbackService feedback = new CustomerFeedbackService(this.clientSocket);
                feedback.Feedback(json_data);
                break;
            case GET_ALL_FEEDBACKS:
                CustomerFeedbackService feedback1 = new CustomerFeedbackService(this.clientSocket);
                feedback1.getAllFeedbacks(json_data);
                break;
            case REMOVE_FEEDBACK:
                CustomerFeedbackService feedback2 = new CustomerFeedbackService(this.clientSocket);
                feedback2.deleteCustomerFeedback(json_data);
            case GET_ALL_PRODUCTS:
                productService.getAllProducts();
                break;
            case DELETE_PRODUCT:
                productService.deleteProduct(json_data);
                break;

            case GET_WINNERS:
                pointsService.getWinners();
                break;
            case GET_PRODUCT_BY_ID:
                productService.getProductById(json_data);
                break;
            case UPDATE_PRODUCT:
                productService.updateProduct(json_data);
                break;
            case CREATE_CUSTOMER:
                customer.create();
                break;
            case GET_ALL_BUSINESSES:
                businessService.getAll();
                break;
            case GET_BUSINESS:
                businessService.getBusinessById(json_data);
                break;
            case AUTHENTICATION:
                AuthService auth = new AuthService(this.clientSocket,this.json_data);
                break;
            case DISABLE_CUSTOMER:
                customer.disable();
                break;
            case CREATE_COUPON:
                couponService.createCoupon(json_data);
                break;
            case REDEEMING_COUPON:
                couponService.redeemCoupon(json_data);
                break;
            case GET_ALL_COUPONS:
                couponService.getAllCoupons(json_data);
                break;
            case GET_ALL_CUSTOMERS:
                customer  = new CustomerService(this.clientSocket,this.json_data);
                customer.readAll();
                break;
            case GET_CUSTOMER:
                customer  = new CustomerService(this.clientSocket,this.json_data);
                customer.readOne();
                break;
            case RENABLE_CUSTOMER:
                customer.renableCard(json_data);
                couponService.getAllCoupons(json_data);
                break;
            case GET_FEATURES:
                featureService.getAllFeature();
                break;
            case GET_ALL_SALES:
//                salesService.getAllSales();
                break;
            case ADD_SALE:
                salesService.buyAProduct(json_data);
                break;
            case GET_ALL_EMPLOYEES:
                employee = new EmployeeService(this.clientSocket,this.json_data);
                employee.readAll();
                break;
            case GET_EMPLOYEE:
                employee = new EmployeeService(this.clientSocket,this.json_data);
                employee.readOne();
            case REGISTER_FEATURE:
                featureService.registerFeature(json_data);
                break;
            case DELETE_FEATURE:
                featureService.deleteFeature(json_data);
                break;
            case UPDATE_FEATURE:
                featureService.update(json_data);
                break;
            case  GET_FEATURE_BY_ID:
                featureService.getFeatureByCode(json_data);
                break;
            default:
                System.out.println("\t\t\tSORRY INVALID API KEY");

        }
    }
}