package com.customify.server.services;

import com.customify.client.SendToServer;
import com.customify.server.Db.Db;
import com.customify.server.SendToClient;
import com.customify.server.response_data_format.customer.CreateCustomerFormat;
import com.customify.shared.Request;
import com.customify.shared.Response;
import com.customify.shared.requests_data_formats.BusinessFormat;
import com.customify.shared.responses_data_format.BusinessFormats.BusinessCreate;
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

//        String query = "INSERT INTO customers VALUES(NULL, ?, ?, ?, ?, ?, ?, NOW())";

//        PreparedStatement statement = connection.prepareStatement(query);
//        statement.setString(1, businessFormat.getLocation());
//        statement.setString(2, businessFormat.getAddress());
//        statement.setString(3, businessFormat.getPhone_number());
//        statement.setString(4, businessFormat.getName());
//        statement.setInt(5, businessFormat.getRepresentative());
//        statement.setInt(6, businessFormat.getPlan());

        // Let me try to execute the query and write the result ....
//        try {
//            if(statement.execute()){
//                System.out.println("Your query not working .... ");
//            }else{
            CreateCustomerFormat format = new CreateCustomerFormat("Successfully registered a customer",201);
            String response_json = objectMapper.writeValueAsString(format);
            responseData.add(response_json);
            SendToClient serverResponse =new  SendToClient(this.socket,this.responseData);
    }

    public void update() throws SQLException{}
    public void disable() throws SQLException{}
    public void readOne() throws SQLException{}
    public void readAll() throws SQLException{}
}
