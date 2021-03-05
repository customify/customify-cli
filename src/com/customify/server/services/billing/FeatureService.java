/**
 * @description
 * server-side feature service for handling operations related to the billing feature
 *
 * @author fiston nshimiyandinze
 * @since Wednesday, 5 February 2021
 * */
package com.customify.server.services.billing;

import com.customify.server.CustomizedObjectOutputStream;
import com.customify.server.Db.Db;
import com.customify.server.SendToClient;
import com.customify.server.data_format.billing.FeatureFormat;
import com.customify.server.response_data_format.billing.ResponseStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.mail.iap.Response;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeatureService {
    Socket socket;
    OutputStream output;
    ObjectOutputStream objectOutput;
    private  int statusCode;

    public FeatureService(Socket socket) throws IOException {
        this.socket =socket;
    }
    /**
     * @author fiston nshimiyandinze
     * @role
     * this function is to handle the backend registering into the database
     * and sending back the response
     * */
    public void registerFeature(String data) throws SQLException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(data);

        Connection connection = Db.getConnection();
        String sqlQuery = "INSERT INTO Features (featureName,featureDescription)  VALUES(?,?)";
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setString(1,jsonNode.get("featureName").asText());
        statement.setString(2,jsonNode.get("featureDescription").asText());

        if(statement.execute()){
            System.out.println("Your query not working .... ");
        }else{
            System.out.println("Query Ok !!! ");
            this.handleStatusResponses(201);
        }
    }

    /**
     * @author fiston nshimiyandinze
     * @role
     * this function is to handle the backend updating into the database
     * and sending back the response
     * */
    public void  update(String data) throws SQLException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(data);

        Connection connection = Db.getConnection();
        String sqlQuery = "UPDATE  Features SET featureName=?,featureDescription =? WHERE featureId=?";

        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setString(1,jsonNode.get("featureName").asText());
        statement.setString(2,jsonNode.get("featureDescription").asText());
        statement.setInt(3,jsonNode.get("featureId").asInt());
        if(statement.execute()){
            System.out.println("Your query not working .... ");
        }else{
            handleStatusResponses(200);
            System.out.println("Query Ok !!! ");
        }
    }

    /**
     * @author fiston nshimiyandinze
     * @role
     * this function is used to handle the backend get  deleting by id from the database
     * and sending back the response
     * */

    public  void deleteFeature(String data)throws  IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(data);

        Statement statement = Db.getStatement();
        this.statusCode = 200;
        try {
            int exec = statement.executeUpdate("delete from Features where featureId="+jsonNode.get("featureId"));
            System.out.println("Excuted delete"+exec);
            if(exec==1){
                System.out.println("Successfully deleted");
                this.handleStatusResponses(200);
            }
        }
        catch (SQLException e){
            this.statusCode= 400;
            System.out.println("Error occured: "+e.getMessage());
        }

    }

    /**
     * @author fiston nshimiyandinze
     * @role
     * this function is to handle the backend get all features from the database
     * and sending back the response
     * */
    public void getFeatureByCode(String request) throws IOException{
        //setting the response status code
        this.statusCode = 200;
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(request);

        //formatting the response into a dataformat
        Statement statement = Db.getStatement();
        List<String> data = new ArrayList<>();
        try{
            ResultSet res = statement.executeQuery("select * from Features  where featureId="+jsonNode.get("featureId"));
            while(res.next()){
                FeatureFormat feature = new FeatureFormat(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3)
                );
                data.add(objectMapper.writeValueAsString(feature));
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

    /**
     * @author fiston nshimiyandinze
     * @role
     * this function is to handle the backend get all features into the database
     * and sending back the response
     * */

    public void getAllFeature() throws IOException {
        System.out.println("Fetching all features");
        ObjectMapper objectMapper = new ObjectMapper();
        //setting the response status code
        this.statusCode = 200;

        //formatting the response into a data format
        Statement statement = Db.getStatement();
        String query = "Select * from Features";
        List<String> data = new ArrayList<>();
        try {
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                FeatureFormat feature = new FeatureFormat(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3)
                );
                data.add(objectMapper.writeValueAsString(feature));
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
