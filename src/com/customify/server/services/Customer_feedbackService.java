package com.customify.server.services;

/*
* @author: NIYONZIMA Stecie
* 
   * @Description
   * This class has a query for inserting data into database
   * done on 9 Feb 2021
*/

import com.customify.shared.Response;
import com.customify.server.Db.Db;
import com.customify.shared.responses_data_format.customer_feedbackFormat.FeedbackSuccessFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.sql.SQLException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.fasterxml.jackson.core.JsonProcessingException;
public class Customer_feedbackService {
    Socket socket;
    OutputStream output;
    ObjectOutputStream objectOutput;

    public Customer_feedbackService(Socket socket) throws IOException {
        this.socket = socket;
        this.output = socket.getOutputStream();
        this.objectOutput = new ObjectOutputStream(output);
    }

    
//    function to send data to the database
    public void Feedback(String data) throws SQLException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(data);
        Connection connection = Db.getConnection();
        String feedbackQuery = "INSERT INTO customerFeedback values (?,?,?,?,NOW())";
        try {

//        	prepare the statement to be sent to the database 
            PreparedStatement statement = connection.prepareStatement(feedbackQuery);
            statement.setInt(1, jsonNode.get("customerId").asInt());
            statement.setInt(2, jsonNode.get("businessId").asInt());
            statement.setString(3, jsonNode.get("title").asText());
            statement.setString(4, jsonNode.get("description").asText());

            if (statement.execute()) {
                System.out.println("Something went wrong");
            } else {
                System.out.println("Query Ok !!! ");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
