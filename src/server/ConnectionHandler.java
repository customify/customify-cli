package server;

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

  public  ConnectionHandler(Socket socket){
    this.clientSocket = socket;
  }

  private boolean readCommand() {
        String s = null;

        try {
            s = (String) is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
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
          this.os.writeObject(o);
          this.os.flush();
      }catch (Exception e){
          System.out.println(Arrays.toString(e.getStackTrace()));
      }
  }

  public void init(){
      try{
          this.is = new ObjectInputStream(this.clientSocket.getInputStream());
          this.os = new ObjectOutputStream(this.clientSocket.getOutputStream());

          while (this.readCommand()){}

      }catch (IOException e) {
          e.printStackTrace();
      }
  }

  public void socketClose(){
      try{ this.os.close();
        this.is.close();
        this.clientSocket.close();
      }catch (Exception e){
          System.out.println("error: "+ Arrays.toString(e.getStackTrace()));
      }
  }
}
