package com.customify.server.controllers;

/**
 * @author: NSENGIYUMVA Gershom
 * done on: 3 Feb 2021
 * 
 * @description
 * This feedback controller takes the data that have been input by the customer
 * to the server
 * */

import com.customify.shared.requests_data_formats.FeedbackFormat;
import com.customify.shared.responses_data_format.AuthFromats.FeedbackSuccessFormat;
import com.customify.server.Db.Db;
import com.customify.shared.Request;
import com.customify.shared.Response;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeedbackController {
    DataOutputStream output;
    private Socket socket;
    private Request request;

    public FeedbackController(Socket socket, Request request) throws IOException {
        this.socket = socket;
        this.request = request;
    }

    // method to send the feedback data to the database
    public void sendDataInDb() throws IOException, SQLException {
    	System.out.println("Something");
        FeedbackFormat format = (FeedbackFormat) request.getObject();

        OutputStream output = this.socket.getOutputStream();
        ObjectOutputStream objectOutput = new ObjectOutputStream(output);
        /**
         * This is all about sending the feedback data to the database
         * 
         * But we need to firstly check if the customer_id and business_id entered by
         * the already there in the database, if not, no feedback can be provided by
         * that customer
         * 
         * --------------------------------------------
         */

        List responseData = new ArrayList<>();

        try {
            Connection connection = Db.getConnection();
            String customerQuery = "INSERT INTO customerFeedback(customer_id, business_id,"
            		+ "title,description,creation_date) VALUES(?,?,?,?,NOW())";

            PreparedStatement statement = connection.prepareStatement(customerQuery);
            statement.setInt(1, format.getCustomerId());
            statement.setInt(2, format.getBusinessId());
            statement.setString(3, format.getTitle());
            statement.setString(4, format.getDescription());

            if (statement.executeUpdate() > 0) {
                Response response = new Response(200, format);
                responseData.add(response);                
            } else {
                Response response = new Response(400, format);
                responseData.add(response);
            }
        } catch (SQLException se) {
            System.out.println(se.getMessage());
            Response response = new Response(500, format);
            responseData.add(response);
        }

        // Sending the response to client
        objectOutput.writeObject(responseData);
    }
}
