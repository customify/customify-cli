package com.customify.client.services;
import com.customify.shared.Keys;
import com.customify.client.Common;
import com.customify.shared.Request;
import com.customify.shared.Response;
import com.customify.shared.requests_data_formats.LoginFormat;
import com.customify.shared.requests_data_formats.SignUpFormat;
import com.customify.shared.responses_data_format.AuthFromats.SuccessLoginFormat;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

public class AuthService {

    private Socket socket;
    private String data;

    InputStream inputStream;
    ObjectInputStream objectInputStream;

    public AuthService(Socket socket){
        this.socket = socket;
    }
    public Socket getSocket()
    {
        return socket;
    }
    public void setSocket(Socket socket) throws IOException {
        this.socket = socket;
        inputStream = this.socket.getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);

    }

    public void login(LoginFormat format) throws IOException, ClassNotFoundException {
        Request request = new Request(Keys.LOGIN,format);
        Common common = new Common(request,this.socket);
        //if the sending is successful call a method to handle response from server
        if(common.sendToServer()==true){
            this.handleLoginResponse();
        }
    }
    public void signUp(SignUpFormat format) throws IOException, ClassNotFoundException {
        Request request = new Request(Keys.REGISTER,format);
        Common common = new Common(request,this.socket);
        if(common.sendToServer()){
            this.handleLoginResponse();
        }
    }

    public void handleLoginResponse() throws IOException, ClassNotFoundException {
        //reading and extracting response from the server
        inputStream = this.socket.getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);
        List<Response> response = (List<Response>) objectInputStream.readObject();
        if(response.get(0).getStatusCode() == 200){
            /*
              If the response status code is OK call SuccessLoginFormat to extract data from server
            */
            SuccessLoginFormat data = (SuccessLoginFormat) response.get(0).getData();
            System.out.println("               Login success full");
            System.out.println("               Registered Email: "+data.getEmail());
        }
    }

}