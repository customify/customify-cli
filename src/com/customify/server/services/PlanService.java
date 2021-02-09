package com.customify.server.services;

//Created and Wrote Whole Document By Moss

import com.customify.server.Db.Db;
import com.customify.shared.Keys;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import java.net.Socket;
import java.sql.*;

public class PlanService {
    Socket socket;
    private final Connection connection = Db.getConnection();
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
                System.out.println("Successfully !");
            }else{
                System.out.println("Not executed !");
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
            return objectMapper.writeValueAsString(results);

        }catch(SQLException | JsonProcessingException e){
            System.out.println(e.getMessage());
        }
        return null;
    }


}
