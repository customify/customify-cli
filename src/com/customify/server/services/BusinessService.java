/**
 * @description
 * The register business front-end services class this is here to
 * register all the businesses in the project must use this service
 *
 * @author Kellia Umuhire, Anselme Habumugisha
 * @since Wednesday, 3 February 2021
 * */

package com.customify.server.services;


import com.customify.shared.Response;
import com.customify.server.Db.Db;
import com.customify.shared.responses_data_format.BusinessFormats.BusinessReadFormat;
import com.customify.shared.responses_data_format.BusinessFormats.BusinessRFormat;
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
    OutputStream output;
    ObjectOutputStream objectOutput;
    private int statusCode;

    public BusinessService(Socket socket)throws IOException{
        this.socket = socket;
        this.output = socket.getOutputStream();
        this.objectOutput = new ObjectOutputStream(output);
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

    /**
     * @author Kellia Umuhire
     * @role
     * Method for handling delete of business
     * */
    public void removeBusiness(String data) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(data);

        Statement statement = Db.getStatement();
        this.statusCode = 200;
        try {
            int ret = statement.executeUpdate("delete from businesses where id="+jsonNode.get("businessId"));
            if(ret==1){
                System.out.println("Successfully deleted");
            }
        }
        catch (SQLException e){
            this.statusCode= 400;
            System.out.println("Error occured: "+e.getMessage());
        }

    }

    /**
     * @author Kellia Umuhire
     * @role
     * Method for handling request for fetching one business by its id
     * */
    public void getBusinessById(String data) throws IOException{
        //setting the response status code
        this.statusCode = 200;
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(data);

        //formatting the response into a dataformat
        Statement statement = Db.getStatement();
        try{
            ResultSet res = statement.executeQuery("select * from businesses where id="+jsonNode.get("businessId"));
            if(res.next()){
                BusinessRFormat bs = new BusinessRFormat(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getInt(6),
                        res.getInt(7)
                );
                String json = objectMapper.writeValueAsString(bs);

                //send
                objectOutput.writeObject(json);
                objectOutput.close();
            }


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @author Kellia Umuhire
     * @role
     * Method for fetching all businesses registered
     * */
    public void getAll() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        //setting the response status code
        this.statusCode = 200;

        //formatting the response into a data format
        Statement statement = Db.getStatement();
        String query = "Select * from businesses";
        List<BusinessRFormat> alldata = new ArrayList<>();
        try {
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                BusinessRFormat bs = new BusinessRFormat(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getInt(6),
                        res.getInt(7)
                );
                alldata.add(bs);
            }
            BusinessReadFormat format = new BusinessReadFormat(alldata);
            String json = objectMapper.writeValueAsString(format);

            //Sending the response to server after it has been formated
            objectOutput.writeObject(json);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
