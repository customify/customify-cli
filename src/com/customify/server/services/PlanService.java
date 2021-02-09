package com.customify.server.services;

//Created and Wrote Whole Document By Moss

import com.customify.server.Db.Db;
import com.customify.server.SendToClient;
import com.customify.shared.Keys;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import java.net.Socket;
import java.sql.*;
import java.util.Collections;

public class PlanService {
    Socket socket;
    private final Connection connection = Db.getConnection();
    private String response;
    public PlanService(Socket socket){
        this.socket = socket;
    }
    public void create(String inputs) throws SQLException, JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(inputs);

        String query = "INSERT INTO plans VALUES (NULL,?,?,NOW())";

        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setString(1,jsonNode.get("planTitle").asText());
        statement.setString(2,jsonNode.get("planDescription").asText());

        try{
            if(statement.execute()){
                this.response = "Plan created successfully";
                SendToClient sendToClient = new SendToClient(socket, Collections.singletonList(response));
            }else{
                System.out.println("Ops failed to execute");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public String read(Keys key){
        try{
            String query = "SELECT * from plans";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(results);
            SendToClient sendToClient = new SendToClient(socket, Collections.singletonList(json));
        }catch(SQLException | JsonProcessingException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void update(String inputs) throws SQLException, JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(inputs);

        String query = "UPDATE plans SET planTitle=? , planDescription=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, jsonNode.get("planTitle").asText());
        statement.setString(2, jsonNode.get("planDescription").asText());

        try{
            if(statement.execute()){
                this.response = "Plan updated successfully";
                SendToClient sendToClient = new SendToClient(socket, Collections.singletonList(response));
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

        String query = "DELETE FROM plans where planId = ?";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1,jsonNode.get("planId").asInt());

        try{
            if (statement.execute()){
                this.response = "Plan Deleted Successfully ";
                SendToClient sendToClient = new SendToClient(socket, Collections.singletonList(response));
            }else{
                System.out.println("Ops Failed to execute");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}
