package com.customify.client.services;
import com.customify.client.SendToServer;
import com.customify.client.data_format.AuthenticationDataFormat;

import com.customify.client.utils.authorization.UserSession;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AuthService {

    private Socket socket;
    private AuthenticationDataFormat data;
    private  boolean authenticated = false;
    private String loggedInUser = null;

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public AuthenticationDataFormat getData() {
        return data;
    }

    public void setData(AuthenticationDataFormat data) {
        this.data = data;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public AuthService(){}
    public AuthService(Socket socket,AuthenticationDataFormat data){
        this.socket = socket;
        this.data = data;
    }

    public Socket getSocket()
    {
        return socket;
    }

    public void setSocket(Socket socket) throws IOException {
        this.socket = socket;
    }


    public boolean authenticate() throws IOException, ClassNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(this.data);

        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {
            this.handleLoginResponse();
        }
        return isAuthenticated();
    }



    public void handleLoginResponse() throws IOException, ClassNotFoundException {

        try {

            InputStream input =this.socket.getInputStream();
            ObjectInputStream objectInput = new ObjectInputStream(input);
            List<String> res = (List) objectInput.readObject();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(res.get(0));


            if(jsonNode.get("status").asInt() == 201)
            {
                UserSession session =new UserSession();
                switch(jsonNode.get("appUser").asText()){
                    case "EMPLOYEE":
                        session.setEmployee(res.get(0));
                        break;
                    case "BUSINESS_ADMIN":
                        session.setBusinessAdmin(res.get(0));
                        break;
                    case "SUPER_ADMIN":
                        session.setSuperAdmin(res.get(0));
                        break;
                    default:
                        System.out.println("Invalid Title");
                }
                setLoggedInUser(jsonNode.get("appUser").asText());
                setAuthenticated(true);
            }
        }catch(Exception e){
            System.out.println( "Exception Caught "+e.getMessage());

    }
}

    public String authenticateAdmin(){
        return  "";
    }

    public String authenticateEmployee() {
        return  "";
    }
    }