package com.customify.server.services.billing;


/**
 * @author Mfuranziza Sekata Aimelyse Moss
 * Created and Wrote Whole Document By Moss
 * */

import com.customify.server.Db.Db;
import com.customify.server.Keys;
import com.customify.server.SendToClient;
import com.customify.server.data_format.billing.FeatureFormat;
import com.customify.server.data_format.billing.PlanFormat;
//import com.customify.server.response_data_format.billing.PlanFormat;
import com.customify.server.CustomizedObjectOutputStream;
import com.customify.server.response_data_format.billing.ResponseStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlanService {
    Socket socket;
    OutputStream output;
    ObjectOutputStream objectOutput;

    private final Connection connection = Db.getConnection();
    private String response;
    public PlanService(Socket socket){
        this.socket = socket;
    }
    public void create(String inputs) throws SQLException, JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(inputs);

        String query = "INSERT INTO Plans (plantTitle, planDescription) VALUES  (?,?)";

        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setString(1,jsonNode.get("planTitle").asText());
        statement.setString(2,jsonNode.get("planDescription").asText());

        try{
            if(statement.execute()){
//                this.response = "Plan created successfully";
                this.handleStatusResponses(400);

            }else{
//                System.out.println("Ops failed to execute");
                this.handleStatusResponses(201);

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void read(Keys key){
        ObjectMapper objectMapper = new ObjectMapper();

        //formatting the response into a data format
        Statement statement = Db.getStatement();
        String query = "Select * from Plans";
        List<String> data = new ArrayList<>();
        try {
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                PlanFormat plan = new PlanFormat(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3)
                );
                data.add(objectMapper.writeValueAsString(plan));
            }

            //Sending the response to server after it has been formated
            System.out.println("Sending response ......"+data);
            this.output = socket.getOutputStream();
            this.objectOutput = new CustomizedObjectOutputStream(this.output);
            objectOutput.writeObject(data);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



    public void readById(String request) throws IOException{
        //setting the response status code
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(request);

        //formatting the response into a dataformat
        Statement statement = Db.getStatement();
        List<String> data = new ArrayList<>();
        try{
            ResultSet res = statement.executeQuery("select * from Plans  where planId="+jsonNode.get("planId"));
            while(res.next()){
                PlanFormat plan = new PlanFormat(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3)
                );
                data.add(objectMapper.writeValueAsString(plan));
            }

            //Sending the response to server after it has been formated
            System.out.println("Sending response ......"+data);
            this.output = socket.getOutputStream();
            this.objectOutput = new CustomizedObjectOutputStream(this.output);
            objectOutput.writeObject(data);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(String inputs) throws SQLException, JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(inputs);

        String query = "UPDATE Plans SET plantTitle=? , planDescription=? WHERE planId = ?   ";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, jsonNode.get("planName").asText());
        statement.setString(2, jsonNode.get("planDescription").asText());
        statement.setInt(3,jsonNode.get("planId").asInt());

        try{
            if(statement.execute()){
                System.out.println("Query not ok");
                this.response = "Plan updated successfully";
                this.handleStatusResponses(400);
            }else{
                System.out.println("Query ok");
                this.handleStatusResponses(200);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void delete(String inputs) throws SQLException, JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(inputs);

        String query = "DELETE FROM Plans where planId = ?";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1,jsonNode.get("planId").asInt());

        try{
            if (statement.execute()){
                this.response = "Query not ok";
                this.handleStatusResponses(400);
            }else{
                System.out.println("Query ok");
                this.handleStatusResponses(200);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void handleStatusResponses(int statusCode) throws IOException {
        /**
         * @author Patrick Niyogitare
         * @role Handling responses with only status code
         * */
        System.out.println("Sending create feature success response");
        ObjectMapper objectMapper = new ObjectMapper();
        //setting the response status code
        List<String> response = new ArrayList<>();
        ResponseStatus status = new ResponseStatus(statusCode);
        response.add(objectMapper.writeValueAsString(status));

        System.out.println("Sending response ......"+response);
        this.output = socket.getOutputStream();
        this.objectOutput = new CustomizedObjectOutputStream(this.output);
        objectOutput.writeObject(response);
    }


}
