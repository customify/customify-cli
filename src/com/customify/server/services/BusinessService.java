/**
 * @description
 * The register business front-end services class this is here to
 * register all the businesses in the project must use this service
 *
 * @author IRUMVA HABUMUGISHA Anselme
 * @version 1
 * @since Wednesday, 3 February 2021 - 08:17 - Time in Nyabihu
 * */

package com.customify.server.services;

import com.customify.server.Db.Db;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BusinessService {
    Socket socket;

    public BusinessService(Socket socket){
        this.socket = socket;
    }

    /**
     * @author IRUMVA HABUMUGISHA Anselme
     * @role
     * this function is to handle the backend registering into the database
     * and sending back the response
     * */

    public void create(String data) throws SQLException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(data);

        Connection connection = Db.getConnection();
        String query = "INSERT INTO businesses VALUES(NULL, ?, ?, ?, ?, ?, ?, NOW())";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, jsonNode.get("name").asText());
        statement.setString(2, jsonNode.get("location").asText());
        statement.setString(3, jsonNode.get("address").asText());
        statement.setString(4, jsonNode.get("phone_number").asText());
        statement.setInt(5, jsonNode.get("representative").asInt());
        statement.setInt(6, jsonNode.get("plan").asInt());

        // Let me try to execute the query and write the result ....
        if(statement.execute()){
            System.out.println("Your query not working .... ");
        }else{
            System.out.println("Query Ok !!! ");
        }
    }

    /**
     * @author IRUMVA HABUMUGISHA Anselme
     * @role
     * this function is to handle the backend editing of the business into the database
     * and sending back the response
     * */
    public void update(String data) throws SQLException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(data);

        Connection connection = Db.getConnection();
        String query = "UPDATE businesses SET location = ? , address = ?, phone_number = ?, name = ?, representative_id = ?, plan_id = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, jsonNode.get("location").asText());
        statement.setString(2, jsonNode.get("address").asText());
        statement.setString(3, jsonNode.get("phone_number").asText());
        statement.setString(4, jsonNode.get("name").asText());
        statement.setInt(5, jsonNode.get("representative").asInt());
        statement.setInt(6, jsonNode.get("plan").asInt());
        statement.setInt(7, jsonNode.get("int").asInt());

        // Let me try to execute the query and write the result ....
        if(statement.execute()){
            System.out.println("Your query not working .... ");
        }else{
            System.out.println("Query Ok !!! ");
        }
    }
}
