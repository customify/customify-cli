package com.customify.server.services;

import com.customify.client.SendToServer;
import com.customify.client.data_format.DeActivateCustomer;
import com.customify.server.CustomizedObjectOutputStream;
import com.customify.server.Db.Db;
import com.customify.server.SendToClient;
import com.customify.server.response_data_format.customer.CreateCustomerFormat;
import com.customify.server.response_data_format.customer.GetAll;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomerService {
    List<String> responseData = new ArrayList<>();
    ObjectOutputStream objectOutput;
    Socket socket;
    OutputStream output;
     String json_data;


    private int statusCode;

    public CustomerService(Socket socket, String json_data) throws IOException, SQLException {
        this.json_data = json_data;
        this.socket = socket;
        System.out.println(socket);

    }

    public CustomerService(Socket socket) {
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

        String query = "INSERT INTO Customer (customer_id,first_name,last_name,email,code) VALUES(?,?, ?, ?, ?)";
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
    public void disable() throws SQLException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(this.json_data);
        String code = jsonNode.get("code").asText();
        String json="";
        try
        {
            Connection connection = Db.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE Customer SET disable = 1 WHERE code = ?");
            // the prepared statement parameters
            statement.setString(1,code);
            // executeUpdate to execute our sql update statement and returns number of rows affected
            int updateCount = statement.executeUpdate();
            statement.close();


            if(updateCount > 0){
                json = "{ \"status\" : \"200\"}";
                System.out.println("Successfully updated");
            }
            else{
                json = "{ \"status\" : \"401\"}";
                System.out.println("Not successfully updated");
            }

        }
        catch (Exception e)
        {

            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            json = "{ \"status\" : \"500\"}";

        }finally {
            responseData.add(json);
            this.output = socket.getOutputStream();
            this.objectOutput = new CustomizedObjectOutputStream(this.output);
            System.out.println("Response "+responseData.get(0));
            objectOutput.writeObject(this.responseData);
        }
    }
    public void readOne() throws SQLException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(this.json_data);
        String received_code = jsonNode.get("customerCode").asText();

        Connection connection = Db.getConnection();
        String query = "SELECT * FROM Customer WHERE code = \"" + received_code +"\"";
        String firstName, lastName, email, code,customerId,stateDesc = "";
        int state;


        String json = "";

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);


            if (!rs.next()) {

                json = "{ \"status\" : \"404\"}";
                responseData.add(json);
            } else {
                customerId = rs.getString("customer_id");
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                email = rs.getString("email");
                code = rs.getString("code");
                state = rs.getInt("disable");
                if(state == 0){
                    stateDesc = "INACTIVE";
                }
                else if(state == 1){
                    stateDesc = "ACTIVE";
                }

                GetAll format = new GetAll(firstName, lastName, email, code, customerId, 200, stateDesc);
                json = objectMapper.writeValueAsString(format);
                responseData.add(json);
            }



        } catch (Exception ex) {
            System.out.println("DB-ERROR " + ex.getMessage());
            json = "{ \"status\" : \"500\"}";
            responseData.add(json);
        } finally {
            this.output = socket.getOutputStream();
            this.objectOutput = new CustomizedObjectOutputStream(this.output);
            objectOutput.writeObject(this.responseData);
        }

    }
    public void readAll() throws SQLException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Connection connection = Db.getConnection();
        String query = "SELECT * FROM Customer";
        String firstName, lastName, email, code,customerId,stateDesc = "";
        int state;
        String json = "";

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (!rs.next()) {
                System.out.println("NO CUSTOMERS FOUND");
                json = "{ \"status\" : \"404\"}";
                responseData.add(json);
            } else {
                while (rs.next()) {
                    customerId = rs.getString("customer_id");
                    firstName = rs.getString("first_name");
                    lastName = rs.getString("last_name");
                    email = rs.getString("email");
                    code = rs.getString("code");
                    state = rs.getInt("disable");

                    if(state == 0){
                        stateDesc = "INACTIVE";
                    }
                    else if(state == 1){
                        stateDesc = "ACTIVE";
                    }

                    GetAll format = new GetAll(firstName, lastName, email, code, customerId, 200, stateDesc);
                    json = objectMapper.writeValueAsString(format);

                    responseData.add(json);
                }

            }
        } catch (Exception ex) {
            System.out.println("DB-ERROR " + ex.getMessage());
            json = "{ \"status\" : \"500\"}";
            responseData.add(json);
        } finally {
            this.output = socket.getOutputStream();
            this.objectOutput = new CustomizedObjectOutputStream(this.output);


            objectOutput.writeObject(this.responseData);
        }
    }
    /**
     * @author Murenzi Confiance Tracy
     * @role
     * this function is to handle the backend deactivate the customer from the database
     * and sending back the response TO THE CLIENT SIDE
     * */
    public void renableCard(String request) throws IOException, SQLException {
        System.out.println("Request to re-enable card was received at server");
        String response = "";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(request);
            String code = jsonNode.get("code").asText();

            String query = "UPDATE Customer SET disable = 0 WHERE code = ?";
            PreparedStatement preparedStatement = Db.getConnection().prepareStatement(query);
            preparedStatement.setString(1,code);

            if(preparedStatement.executeUpdate() > 0){
                System.out.println("Card was re-enabled");
                response = "{ \"status\" : \"200\"}";
            }
            else{
                response = "{ \"status\" : \"400\"}";
            }
        } catch (JsonProcessingException e) {
            response = "{ \"status\" : \"500\"}";
        } catch (SQLException e) {
            response = "{ \"status\" : \"500\"}";
        } finally {
            responseData.clear();
            responseData.add(response);
            this.output = socket.getOutputStream();
            this.objectOutput = new CustomizedObjectOutputStream(this.output);
            System.out.println("Response "+responseData.get(0));
            objectOutput.writeObject(this.responseData);
        }

    }

}
