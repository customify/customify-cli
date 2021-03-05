package com.customify.server.services;



import com.customify.client.SendToServer;
import com.customify.server.CustomizedObjectOutputStream;
import com.customify.server.Db.Db;
import com.customify.server.SendToClient;
import com.customify.server.response_data_format.employee.GetAll;
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

public class EmployeeService {
    List<String> responseData = new ArrayList<>();
    ObjectOutputStream objectOutput;
    Socket socket;
    OutputStream output;
    String json_data;


    private int statusCode;

    public EmployeeService(Socket socket, String json_data) throws IOException, SQLException {
        this.json_data = json_data;
        this.socket = socket;
        System.out.println(socket);

    }

    public EmployeeService(Socket socket) {
        this.socket = socket;
    }

    public void create() throws SQLException, IOException {
    }
    public void readOne() throws SQLException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(this.json_data);
        String received_code = jsonNode.get("employeeId").asText();
        int i = Integer.parseInt(received_code.trim());

        Connection connection = Db.getConnection();
        String query = "SELECT * FROM Employee WHERE emp_id ="+ i;
        String firstName, lastName, email, title;
        int employeeId;
        int state;


        String json = "";

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);


            if (!rs.next()) {

                json = "{ \"status\" : \"404\"}";
                responseData.add(json);
            } else {
                employeeId = rs.getInt("emp_id");
                firstName = rs.getString("firname");
                lastName = rs.getString("lasname");
                email = rs.getString("email");
                title = rs.getString("title");
                GetAll format = new GetAll(firstName, lastName, email, employeeId , title, 200);
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
        String query = "SELECT * FROM Employee";
        String firstName, lastName, email, title;
        int employeeId;
        String json = "";

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (!rs.next()) {
                System.out.println("NO EMPLOYEES FOUND");
                json = "{ \"status\" : \"404\"}";
                responseData.add(json);
            } else {
                while (rs.next()) {
                    employeeId = rs.getInt("emp_id");
                    firstName = rs.getString("firname");
                    lastName = rs.getString("lasname");
                    email = rs.getString("email");
                    title = rs.getString("title");
                    GetAll format = new GetAll(firstName, lastName, email, employeeId , title, 200);
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



}

