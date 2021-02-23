package com.customify.server.services;

import com.customify.server.Db.Db;
import com.customify.server.SendToClient;
import com.customify.server.response_data_format.customer.CreateCustomerFormat;
import com.customify.shared.Response;
import com.customify.shared.requests_data_formats.ProductFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    List<String> responseData = new ArrayList<>();
    ObjectOutputStream objectOutput;
    Socket socket;
     String json_data;

    public CustomerService(Socket socket, String json_data){
        this.json_data = json_data;
        this.socket = socket;
    }

    /**
     * @author SAMUEL DUSHIMIMANA
     * @role
     * this function is to handle the backend registering A CUSTOMER into the database
     * and sending back the response TO THE CLIENT SIDE
     * */

    public void create() throws SQLException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json_data);
        String email = jsonNode.get("email").asText();
        String firName = jsonNode.get("firName").asText();
        String lasName = jsonNode.get("lasName").asText();


        Connection connection = Db.getConnection();

        String query = "INSERT INTO customers (customer_id,first_name,last_name,email,code) VALUES(?,?, ?, ?, ?)";
        CreateCustomerFormat format;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, 34333);
            statement.setString(2, firName);
            statement.setString(3, lasName);
            statement.setString(4, email);
            statement.setString(5, "564-TSA-565");

            int i = statement.executeUpdate();
            if (i > 0) {
//                System.out.println("success");
             format = new CreateCustomerFormat("Successfully registered a customer",201);
            format.setJson_data(json_data);


            } else {
                System.out.println("stuck somewhere");
                format = new CreateCustomerFormat("STUCK SOMEWHERE",201);

            }

        } catch (Exception e)
        {
            format = new CreateCustomerFormat("STUCK SOMEWHERE",201);
            String response_json = objectMapper.writeValueAsString(format);
            responseData.add(response_json);
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        } finally{
            SendToClient serverResponse =new  SendToClient(this.socket,this.responseData);
        }


    }

    /**
     * @author Nshimiye Emmy
     * @role
     * this service method is to update the customer in the database that means the server side
     * */
    public void update() throws SQLException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json_data);

        OutputStream output = this.socket.getOutputStream();
        ObjectOutputStream objectOutput =  new ObjectOutputStream(output);

        Statement stmt = null;
        Connection conn = null;

        try {
            conn = Db.getConnection();

           // System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "UPDATE customers SET customer_code = "+jsonNode.get("customer_code").asText()+",email = "+jsonNode.get("email").asText()+
                    ",firstName="+jsonNode.get("firstName").asText()+",lastName="+jsonNode.get("lastName").asText()+", WHERE customer_code = "+jsonNode.get("customer_code").asText();

            stmt.executeUpdate(sql);

//            List responseData = new ArrayList<>();
//            Response response = new Response(200,customers);
//            responseData.add(response);

            //Sending the response to client
//            objectOutput.writeObject(responseData);

            stmt.close();
            conn.close();
        }
        catch (Exception e){

        }
    }

    /**
     * @author Murenzi Confiance Tracy
     * @role
     * this function is to handle the backend disable of the customer from the database
     * and sending back the response TO THE CLIENT SIDE
     * */
    public void disable() throws SQLException, JsonProcessingException {
        System.out.println("The data has been successfully reached to the server");

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(this.json_data);
        String code = jsonNode.get("code").asText();
        int createdById = jsonNode.get("createdById").asInt();

        Connection connection = Db.getConnection();
        try
        {
            PreparedStatement statement = connection.prepareStatement("UPDATE customers SET disabled = 1 WHERE code = ?");

            // the prepared statement parameters
            statement.setString(1,code);
            // executeUpdate to execute our sql update statement and returns number of rows affected
            int updateCount = statement.executeUpdate();
            statement.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
    public void readOne() throws SQLException{}
    public void readAll() throws SQLException{}
}
