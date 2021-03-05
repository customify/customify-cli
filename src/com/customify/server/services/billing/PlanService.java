package com.customify.server.services.billing;


/**
 * @author Mfuranziza Sekata Aimelyse Moss
 * Created and Wrote Whole Document By Moss
 * */

import com.customify.server.Db.Db;
import com.customify.server.Keys;
import com.customify.server.SendToClient;
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

        String query = "INSERT INTO Plans (planTitle, planDescription) VALUES  (?,?,NOW())";

        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setString(1,jsonNode.get("planTitle").asText());
        statement.setString(2,jsonNode.get("planDescription").asText());

        try{
            if(statement.execute()){
                this.response = "Plan created successfully";
                this.handleStatusResponses(201);
            }else{
                System.out.println("Ops failed to execute");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void read(Keys key){
//        try{
//            String query = "SELECT * from Plans";
//            Statement statement = connection.createStatement();
//            ResultSet results = statement.executeQuery(query);
//            List<PlanFormat> response = new ArrayList<>();
//            while(results.next()){
//                PlanFormat planFormat = new PlanFormat(
//                        results.getInt(1),
//                        results.getString(2),
//                        results.getString(3)
//                );
//                response.add(planFormat);
//            }
//            ObjectMapper objectMapper = new ObjectMapper();
//            String json = objectMapper.writeValueAsString(response);
//            SendToClient sendToClient = new SendToClient(socket, Collections.singletonList(json));
//            if(sendToClient.send()){
//                System.out.println("Response sent!");
//            }else{
//                System.out.println("Response failed !");
//            }
//        }catch(SQLException | JsonProcessingException e){
//            System.out.println(e.getMessage());
//        }

        System.out.println("Fetching all features");
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



    public String readById(String inputs) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(inputs);
        try{
            String query = "SELECT * from Plans where planId=";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query+jsonNode.get("planId"));
            List<PlanFormat> response = new ArrayList<>();
            while(results.next()){
                PlanFormat planFormat = new PlanFormat(
                        results.getInt(1),
                        results.getString(2),
                        results.getString(3)
                );
                response.add(planFormat);
            }
            ObjectMapper objectMapper1 = new ObjectMapper();
            String json = objectMapper1.writeValueAsString(response);
            SendToClient sendToClient = new SendToClient(socket, Collections.singletonList(json));
            if(sendToClient.send()){
                System.out.println("Response sent!");
            }else{
                System.out.println("Response failed !");
            }
        }catch(SQLException | JsonProcessingException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void update(String inputs) throws SQLException, JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(inputs);

        String query = "UPDATE Plans SET planTitle=? , planDescription=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, jsonNode.get("planTitle").asText());
        statement.setString(2, jsonNode.get("planDescription").asText());

        try{
            if(statement.execute()){
                this.response = "Plan updated successfully";
                SendToClient sendToClient = new SendToClient(socket, Collections.singletonList(response));
                if(sendToClient.send()){
                    System.out.println("Response sent!");
                }else{
                    System.out.println("Response failed !");
                }
            }else{
                System.out.println("Ops failed to execute");
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
                this.response = "Plan Deleted Successfully ";
                SendToClient sendToClient = new SendToClient(socket, Collections.singletonList(response));
                if(sendToClient.send()){
                    System.out.println("Response sent!");
                }else{
                    System.out.println("Response failed !");
                }
            }else{
                System.out.println("Ops Failed to execute");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void handleStatusResponses(int statusCode) throws IOException {
        System.out.println("Sending create feature success response");
        ObjectMapper objectMapper = new ObjectMapper();
        //setting the response status code
        List<String> response = new ArrayList<>();
        ResponseStatus status = new ResponseStatus(statusCode);
        response.add(objectMapper.writeValueAsString(status));

        System.out.println("Sending response ......"+response);
        this.output = socket.getOutputStream();
        this.objectOutput = new CustomizedObjectOutputStream(this.output);
        System.out.println("Response "+response.get(0));
        objectOutput.writeObject(response);
    }


}
