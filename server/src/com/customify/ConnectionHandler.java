package com.customify;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class ConnectionHandler {
    private Socket clientSocket = null;
    private final ServerSocket server  = null;
    private final DataInputStream in = null;
    private ObjectInputStream is = null;
    private ObjectOutputStream os  = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;

    public  ConnectionHandler(Socket socket){
        this.clientSocket = socket;
    }

    private boolean readCommand() {
//        String s = null;
          BufferedReader s = null;
//        try {
//            s = (String) is.readObject();
              s = new BufferedReader(new InputStreamReader((System.in)));
//        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("Exception 1: "+e.getMessage());

//            return false;
//        }
        System.out.println("01. received object from client");
        // add a valid string object
        return true;
    }
    public void sendError(String message){
        this.send("Error:"+message);
    }

    public void send(Object o){
        try{
            System.out.println("Sending "+(o)+" to the client");
//            this.os.writeObject(o);
//            this.os.flush();
            this.output.writeUTF("Got request");
            this.output.flush();
        }catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void init(){
        try{
//            this.is = new ObjectInputStream(this.clientSocket.getInputStream());
//            this.os = new ObjectOutputStream(this.clientSocket.getOutputStream());
              this.input = new DataInputStream(this.clientSocket.getInputStream());
              this.output = new DataOutputStream(this.clientSocket.getOutputStream());

//              while (this.readCommand()){
//                  System.out.println("User input "+input);
//                  this.output.writeUTF("We get you");
//                }
              String request = "";
              while(!request.equals("exit")){
                  request = this.input.readUTF();
                  System.out.println("Request form user: "+request);
                  this.output.writeUTF("You said: "+request);

              }

        }catch (IOException e) {
            System.out.println("Working on "+e.getMessage());
            //e.printStackTrace();
        }
    }

    public void socketClose(){
        try{ this.output.close();
            this.input.close();
            this.clientSocket.close();
        }catch (Exception e){
            System.out.println("error: "+e.getMessage());
        }
    }

}
