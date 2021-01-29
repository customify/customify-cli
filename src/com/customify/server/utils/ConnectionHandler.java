package customify.server.utils;

import customify.shared.Request;
import customify.server.routes.HandleRoutes;

import java.io.*;
import java.net.*;
import java.util.*;

public class ConnectionHandler {
    private final Socket clientSocket;
    private InputStream input;
    private ObjectInputStream objectInput;

    public  ConnectionHandler(Socket socket){
        this.clientSocket = socket;
    }

    public void init(){
        try{
            this.input = this.clientSocket.getInputStream();
            this.objectInput = new ObjectInputStream(input);
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
        }
    }
    /*public void socketClose(){
        try{ this.output.close();
            this.input.close();
            this.clientSocket.close();
        }catch (Exception e){
            System.out.println("error: "+e.getMessage());
        }
    }
     */
}
