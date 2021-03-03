package com.customify.server.services;

import com.customify.server.CustomizedObjectOutputStream;
import com.customify.server.Db.Db;
import com.customify.server.response_data_format.authentication.AuthAdmin;
import com.customify.server.response_data_format.authentication.AuthEmployee;
import com.customify.server.response_data_format.authentication.AuthSuperAdmin;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthService {
    Socket socket;
    OutputStream output;
    ObjectOutputStream objectOutput;
    private int statusCode;
    List responseData = new ArrayList<String>();

private String json_data;

   public AuthService(Socket socket,String json_data) throws IOException, SQLException {
       this.socket = socket;
       System.out.println(socket);
       this.json_data = json_data;
       this.login();
   }

public void login() throws IOException, SQLException {

    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = objectMapper.readTree(json_data);
    String email = jsonNode.get("email").asText();
    String password = jsonNode.get("password").asText();

    Connection connection = Db.getConnection();

    String query = "SELECT * FROM Employee WHERE password='"+password+"' AND email='"+email+"'";

    try {
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        String id = "", firstName = "", mail = "",tel="" ,title = "", lastName = "", pass = "", createdAt = "", business_id = "";

        String json;

        if (!rs.next()) {
            query = "SELECT * FROM SuperAdmin WHERE password='"+password+"' AND email='"+email+"'";
            rs = st.executeQuery(query);

            if (!rs.next()){
                json = "{ \"status\" : \"401\"}";
            }else{
                pass = rs.getString("password");
                firstName = rs.getString("firName");
                lastName = rs.getString("lasName");
                tel = rs.getString("tel");
                id = rs.getString("id");
                mail = rs.getString("email");
                AuthSuperAdmin superAdminFormat;
                superAdminFormat = new AuthSuperAdmin("SUPER_ADMIN",mail,firstName,lastName, id,tel,201);
                json = objectMapper.writeValueAsString(superAdminFormat);
            }
        }else{
            pass = rs.getString("password");
            firstName = rs.getString("firName");
            lastName = rs.getString("lasName");
            title = rs.getString("title");
            id = rs.getString("emp_id");
            mail = rs.getString("email");
            business_id = rs.getString("business_id");
            createdAt = rs.getString("createdAt");
            AuthAdmin  adminFormat;
            AuthEmployee empFormat;

            if(title.equals("BUSINESS_ADMIN"))
            {
                adminFormat = new AuthAdmin("BUSINESS_ADMIN",mail,firstName,lastName, id,business_id,title,201);
                json = objectMapper.writeValueAsString(adminFormat);
            } else{
                empFormat = new AuthEmployee("EMPLOYEE",mail,firstName,lastName, id,business_id,title,201,createdAt);
                json = objectMapper.writeValueAsString(empFormat);
            }

        }

        responseData.add(json);
    }catch(Exception ex){
        System.out.println(ex);

       String json = "{ \"status\" : \"401\"}";
        responseData.add(json);
    }finally{

        this.output = socket.getOutputStream();
        this.objectOutput = new CustomizedObjectOutputStream(this.output);
        System.out.println("Response "+responseData.get(0));
        objectOutput.writeObject(this.responseData);

    }
  }
}