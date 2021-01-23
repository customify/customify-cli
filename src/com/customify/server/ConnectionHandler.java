package customify.server;

//import com.customify.Request;
import customify.Request;

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
