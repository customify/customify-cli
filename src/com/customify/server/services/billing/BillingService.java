package com.customify.server.services.billing;


/**
 * @author Mfuranziza Sekata Aimelyse Moss
 * Created and Wrote Whole Document By Moss
 * */

import com.customify.client.Keys;
import com.customify.server.Db.Db;
import com.customify.server.SendToClient;
import com.customify.server.response_data_format.billing.BillingFormat;
import com.customify.server.response_data_format.billing.PlanFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BillingService {
    Socket socket;
    private final Connection connection = Db.getConnection();
    private String response;
    public BillingService(){}
    public BillingService(Socket socket){
        this.socket = socket;
    }
    public void create(String inputs) throws SQLException, JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(inputs);

        String query = "INSERT INTO Billing VALUES (NULL,?,?,NOW())";

        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setInt(1,jsonNode.get("featureId").asInt());
        statement.setInt(2,jsonNode.get("planId").asInt());

        try{
            if(statement.execute()){
                this.response = "Billing created successfully";
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
    public void read(Keys key){
        try{
            String query = "SELECT * from Billing";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);
            List<BillingFormat> response = new ArrayList<>();
            while(results.next()){
                BillingFormat billingFormat = new BillingFormat(
                        results.getInt(1),
                        results.getInt(2),
                        results.getInt(3)
                );
                response.add(billingFormat);
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
    }
    public void readById(String inputs) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(inputs);
        try{
            String query = "SELECT Billing.billingId, Features.featureName, Plans.planName " +
                    "from Billing,Features where Feature.featureId = Billing.featureId AND billingId=";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query+jsonNode.get("billingId"));
            List<BillingFormat> response = new ArrayList<>();
            while(results.next()){
                BillingFormat billingFormat = new BillingFormat(
                        results.getInt(1),
                        results.getString(2),
                        results.getString(3)
                );
                response.add(billingFormat);
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
    }
    public void update(String inputs) throws SQLException, JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(inputs);

        String query = "UPDATE Billing SET featureId=? , planId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, jsonNode.get("featureId").asText());
        statement.setString(2, jsonNode.get("planId").asText());

        try{
            if(statement.execute()){
                this.response = "Billing updated successfully";
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

        String query = "DELETE FROM Billing where billingId = ?";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1,jsonNode.get("billingId").asInt());

        try{
            if (statement.execute()){
                this.response = "Billing Deleted Successfully ";
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
