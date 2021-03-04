package com.customify.server.services.billing;


/**
 * @author Mfuranziza Sekata Aimelyse Moss
 * Created and Wrote Whole Document By Moss
 * */

import com.customify.client.Keys;
import com.customify.server.Db.Db;
import com.customify.server.SendToClient;
import com.customify.server.response_data_format.billing.PlanFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

        String query = "INSERT INTO Plans VALUES (NULL,?,?,NOW())";

        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setString(1,jsonNode.get("planTitle").asText());
        statement.setString(2,jsonNode.get("planDescription").asText());

        try{
            if(statement.execute()){
                this.response = "Plan created successfully";
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
    public String read(Keys key){
        try{
            String query = "SELECT * from Plans";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);
            List<PlanFormat> response = new ArrayList<>();
            while(results.next()){
                PlanFormat planFormat = new PlanFormat(
                        results.getInt(1),
                        results.getString(2),
                        results.getString(3)
                );
                response.add(planFormat);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(response);
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


}
