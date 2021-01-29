package customify.server.utils;

import customify.server.controllers.Auth;
import customify.shared.Request;
import customify.server.routes.HandleRoutes;
import customify.shared.data_format.LoginFormat;

import java.io.*;
import java.net.*;
import java.util.*;

public class ConnectionHandler {
    private final Socket clientSocket;
    private InputStream input;
    private ObjectInputStream objectInput;
    private Request request;

    public  ConnectionHandler(Socket socket){
        this.clientSocket = socket;
    }

    public void init() {
        try {
            this.input = this.clientSocket.getInputStream();
            this.objectInput = new ObjectInputStream(input);
            while (true) {

                    try{
                        List<Request> clientRequest = (List<Request>) this.objectInput.readObject();
                        this.request = clientRequest.get(0);
                        //                    System.out.println("-------------------QUERY FROM FRONTEND--------------------");
//                    clientRequest.forEach((data) -> System.out.println("Key " + data.getKey() + " value: " + data.getObject().toString()));
                        this.handleRequest();
                    }catch(IOException | ClassNotFoundException  e){}
            } } catch (IOException e) {
                System.out.println("Error in reading Object " + e.getMessage());
        }
    }


    public void handleRequest() throws IOException {
      Auth auth;
        switch (request.getKey()) {
            case "USER_LOGIN":
                 auth = new Auth(this.clientSocket,this.request);
                  auth.login();
                 break;
            case "USER_SIGNUP":
                  auth = new Auth(this.clientSocket,this.request);
                  auth.signup();
                break;
            default:
                System.out.println("\t\t\tSORRY INVALID API KEY");
        }
    }
}