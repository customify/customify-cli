package com.customify.server.services;

import com.customify.shared.Response;
import com.customify.shared.requests_data_formats.LoginFormat;
import com.customify.shared.responses_data_format.AuthFromats.SuccessLoginFormat;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AuthService {
    Socket socket;
    OutputStream output;
    ObjectOutputStream objectOutput;
    private int statusCode;
    Response response;
    List responseData = new ArrayList<>();

   public AuthService(Socket socket) throws IOException {
       this.socket = socket;
       this.output = socket.getOutputStream();
       this.objectOutput = new ObjectOutputStream(output);
   }
   /*
     Method to send feedback to client.
     A serializable class 'Response' Is used to to cast the response
     Like SuccessLoginFormat is used, each response is to have Class to format a response type
     In this case we consider the login process to have been successful & returning 200 statusCode
   */
   public void login(LoginFormat loginFormat) throws IOException {
       //setting the response status code
       this.statusCode = 200;

       //formatting the response into a successLoginFormat
       SuccessLoginFormat format = new SuccessLoginFormat(loginFormat.getEmail());
       response = new Response(statusCode,format);
       responseData.add(response);

       //Sending the response to server after it has been formated
       objectOutput.writeObject(responseData);
   }
}
