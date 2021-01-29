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
        int iterate = 1;
        try {
            this.input = this.clientSocket.getInputStream();
            this.objectInput = new ObjectInputStream(input);
<<<<<<< HEAD
            while (true) {

                    try{
                        List<Request> clientRequest = (List<Request>) this.objectInput.readObject();
                        this.request = clientRequest.get(0);
                        //                    System.out.println("-------------------QUERY FROM FRONTEND--------------------");
//                    clientRequest.forEach((data) -> System.out.println("Key " + data.getKey() + " value: " + data.getObject().toString()));
                        this.handleRequest();
                    }catch(IOException | ClassNotFoundException  e){}
            } } catch (IOException e) {
                System.out.println("Error in reading Object " + e.getMessage()+" ITERATOR "+iterate);
=======
            String request = "";
            while(!request.equals("exit")){
                List<Request> clientRequest = (List<Request>) this.objectInput.readObject();
                System.out.println("-------------------QUERY FROM FRONTEND--------------------");
                clientRequest.forEach((data)-> System.out.println("Key "+data.getKey()+" value: "+data.getRequestData()));
                HandleRoutes handleRoutes = new HandleRoutes(clientRequest.get(0).getKey(), this.clientSocket);
                handleRoutes.switchRoutes();

                DataInputStream response = new DataInputStream(this.clientSocket.getInputStream());
                System.out.println(response.readUTF());
            }

        }catch (IOException | ClassNotFoundException e) {
            System.out.println("Error in reading Object "+e.toString());
>>>>>>> d855d62be74518be1a3d950c6e862a346d57d4a1
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