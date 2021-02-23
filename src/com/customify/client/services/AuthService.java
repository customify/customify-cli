package com.customify.client.services;
import com.customify.client.SendToServer;
import com.customify.client.data_format.AuthenticationDataFormat;
import com.customify.shared.Keys;
import com.customify.client.Common;
import com.customify.shared.Request;
import com.customify.shared.Response;
import com.customify.shared.requests_data_formats.LoginFormat;
import com.customify.shared.requests_data_formats.SignUpFormat;
import com.customify.shared.responses_data_format.AuthFromats.SuccessLoginFormat;
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


    public String authenticateAdmin() throws IOException, ClassNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        String adminId = null;

        String json = objectMapper.writeValueAsString(this.data);

        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {
            this.handleLoginResponse();
        }
     return adminId;
    }

    public String authenticateEmployee() throws IOException, ClassNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        String employeeId = null;
        String json = objectMapper.writeValueAsString(this.data);
//        SendToServer serverSend = new SendToServer(json, this.socket);
//        if (serverSend.send()) {
////            this.handle
//        }
return employeeId;
    }


    public void handleLoginResponse() throws IOException, ClassNotFoundException {

        try {
            InputStream input =this.socket.getInputStream();
            ObjectInputStream objectInput = new ObjectInputStream(input);
            List<String> data = (List) objectInput.readObject();
                System.out.println(data.get(0));
        }catch(Exception e){
            System.out.println("Exception Caught");
        }


    }

}
