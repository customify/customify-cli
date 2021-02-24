package com.customify.server.services;

import com.customify.server.CustomizedObjectOutputStream;
import com.customify.server.Db.Db;
import com.customify.server.SendToClient;
import com.customify.server.response_data_format.AuthenticationResponseFormat;
import com.customify.shared.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    Response response;
    List responseData = new ArrayList<String>();
private String json_data;

   public AuthService(Socket socket,String json_data) throws IOException, SQLException {
       this.socket = socket;
       System.out.println(socket);
       this.json_data = json_data;
       this.login();
   }
   /*
     Method to send feedback to client.
     A serializable class 'Response' Is used to to cast the response
     Like SuccessLoginFormat is used, each response is to have Class to format a response type
     In this case we consider the login process to have been successful & returning 200 statusCode
   */


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
        String employee_id = "", firstName = "", mail = "", title = "", lastName = "", pass = "";
        while (rs.next()) {
            pass = rs.getString("password");
            firstName = rs.getString("firName");
            lastName = rs.getString("lasName");
            title = rs.getString("title");
            employee_id = rs.getString("emp_id");
            mail = rs.getString("email");
        }

        AuthenticationResponseFormat   format = new AuthenticationResponseFormat(mail, firstName, lastName, employee_id, title, 0, 201);
        String json = objectMapper.writeValueAsString(format);
        responseData.add(json);
    }catch(Exception ex){
        System.out.println(ex);
        AuthenticationResponseFormat  format  = new AuthenticationResponseFormat(null,null, null, null,null,0,401);
        String json = objectMapper.writeValueAsString(format);
        responseData.add(json);
    }finally{
       this.output = socket.getOutputStream();
        this.objectOutput = new CustomizedObjectOutputStream(this.output);
        objectOutput.writeObject(this.responseData);
        objectOutput.flush();
        this.output.flush();
        System.out.println("Response "+responseData.get(0));
    }
}
}