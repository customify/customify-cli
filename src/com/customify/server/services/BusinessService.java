/**
 * @description
 * The register business front-end services class this is here to
 * register all the businesses in the project must use this service
 *
 * @author Kellia Umuhire, Anselme Habumugisha
 * @since Wednesday, 3 February 2021
 * */

package com.customify.server.services;
import com.customify.server.CustomizedObjectOutputStream;
import com.customify.server.Db.Db;
import com.customify.server.data_format.business.BusinessRFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusinessService {
    Socket socket;
    OutputStream output;
    ObjectOutputStream objectOutput;
    String statusCode;

    /**
     * Class Constructor
     *
     * @author IRUMVA HABUMUGISHA Anselme
     * @param socket The Socket to use in our Sending and Receiving the request
     * */
    public BusinessService(Socket socket)throws IOException{
        this.socket = socket;
        this.output = socket.getOutputStream();
        this.objectOutput = new ObjectOutputStream(output);
    }

    /**
     * @author IRUMVA HABUMUGISHA Anselme
     * @param data The data from the clint in the JSON Format
     * @role
     * this function is to handle the backend registering into the database
     * and sending back the response
     * */
    public void create(String data) throws SQLException, JsonProcessingException {
        try {
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
                objectOutput.writeObject("{\"status\": 500}");
            }else{
                objectOutput.writeObject("{\"status\": 201}");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * @role
     * this function is to handle the backend editing of the business into the database
     * and sending back the response
     *
     * @author IRUMVA HABUMUGISHA Anselme
     * @param data The data from the clint in the JSON Format
     * */
    public void update(String data) {
        try {
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
            statement.setInt(7, jsonNode.get("id").asInt());

            // Let me try to execute the query and write the result ....
            if(statement.execute()){
                objectOutput.writeObject("{\"status\": 500}");
            }else{
                objectOutput.writeObject("{\"status\": 200}");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
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
        String json="";

        try {
            int ret = statement.executeUpdate("delete from businesses where id="+jsonNode.get("businessId").asInt());
            if(ret==1){
                json = "{\"message\" : \""+"Successfully deleted"+"\", \"statusCode\" : \""+ 200 +"\" }";
                objectOutput.writeObject(json);
            }
        }
        catch (SQLException e){
            json = "{\"message\" : \""+e.getMessage()+"\", \"statusCode\" : \""+ 400 +"\" }";
        }
        finally{

            this.output = socket.getOutputStream();
            this.objectOutput = new CustomizedObjectOutputStream(this.output);
            objectOutput.writeObject(json);
        }

    }

    /**
     * @author Kellia Umuhire
     * @role
     * Method for handling request for fetching one business by its id
     * */
    public void getBusinessById(String data) throws IOException{
        //setting the response status code
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(data);

        //formatting the response into a data format
        Statement statement = Db.getStatement();
        String json="";
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
                        res.getInt(7),
                        res.getDate(8).toString()
                );
                json = objectMapper.writeValueAsString(bs);

            }else{
                json = "{ \"message\" : \""+"Not found"+"\", \"statusCode\" : \""+ 500 +"\" }";
            }
        }
        catch (Exception e){
            json = "{ \"message\" : \""+e.getMessage()+"\", \"statusCode\" : \""+ 500 +"\" }";
        }
        finally{

            this.output = socket.getOutputStream();
            this.objectOutput = new CustomizedObjectOutputStream(this.output);
            objectOutput.writeObject(json);
        }
    }


    /**
     * @author Kellia Umuhire
     * @role
     * Method for fetching all businesses registered
     * */
    public void getAll() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        //formatting the response into a data format
        Statement statement = Db.getStatement();
        String query = "Select * from businesses";
        List<String> alldata = new ArrayList<>();

        try {
            this.statusCode = "200";
            alldata.add(this.statusCode);
            ResultSet res = statement.executeQuery(query);
            String data;
            while(res.next()){

                BusinessRFormat bs = new BusinessRFormat(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getInt(6),
                        res.getInt(7),
                        res.getDate(8).toString()
                );
                data = objectMapper.writeValueAsString(bs);
                alldata.add(data);
            }
        }
        catch (Exception e){
            this.statusCode = "500";
            alldata.add(this.statusCode);
        }
        finally{
            this.output = socket.getOutputStream();
            this.objectOutput = new CustomizedObjectOutputStream(this.output);
            objectOutput.writeObject(alldata);

        }

    }
}
