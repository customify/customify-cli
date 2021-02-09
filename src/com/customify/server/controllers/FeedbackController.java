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
// import com.customify.client.data_format.business.BusinessFormat;
// import com.customify.shared.responses_data_format.BusinessFormats.BusinessCreate;

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
    List<Response> responseData = new ArrayList<>();
    ObjectOutputStream objectOutput;

    public FeedbackController(Socket socket, Request request) throws IOException {
        this.socket = socket;
        this.request = request;
    }

    public void sendDataInDb() throws SQLException {
        FeedbackFormat format = (FeedbackFormat) request.getObject();
        Connection connection = Db.getConnection();
        String customerQuery = "INSERT INTO customerFeedback VALUES(?,?,?,?,NOW())";

        /**
         * This is all about sending the feedback data to the database
         * 
         * But we need to firstly check if the customer_id and business_id entered by
         * the already there in the database, if not, no feedback can be provided by
         * that customer
         * 
         * --------------------------------------------
         */
        PreparedStatement statement = connection.prepareStatement(customerQuery);
        statement.setInt(10, format.getCustomerId());
        statement.setInt(10, format.getBusinessId());
        statement.setString(1, format.getTitle());
        statement.setString(1, format.getDescription());
        try {
            if (statement.execute()) {
                System.out.println("Something went wrong in the query");
            } else {
                FeedbackSuccessFormat successFormat = new FeedbackSuccessFormat("Data sent successively");
                Response response = new Response(201, successFormat);

                OutputStream output = socket.getOutputStream();
                this.objectOutput = new ObjectOutputStream(output);

                responseData.add(response);
                objectOutput.writeObject(responseData);
                System.out.println("The data sent successively");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
