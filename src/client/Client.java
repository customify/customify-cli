package client;

import java.io.*;
import java.net.*;

public class Client {
    private ObjectOutput os = null;
    private ObjectInputStream is = null;

    public Client(String serverIP){
        if(!connectTOServer(serverIP)){
            System.out.println("Failed to connect to the server to: "+serverIP);
        }
    }

    public static void Main(String[] args){
        System.out.println("Entity management is starting..........");
        if(args.length == 1){
            Client theApp = new Client(args[0]);
        }else{
            System.out.println("You must provide address of the server.");
        }
        System.out.println("End................");
    }

    private boolean connectTOServer(String serverIp){
        int portNumber = 3000;
        try{

            Socket socket = new Socket(serverIp, portNumber);
         this.os = new ObjectOutputStream(socket.getOutputStream());
         this.is = new ObjectInputStream(socket.getInputStream());

         System.out.println("Connected to the server "+ socket.getInetAddress() + " on port "+ socket.getPort());
         System.out.println("from local Address: "+ socket.getLocalAddress()+" and port "+ socket.getLocalPort());
     }catch (Exception e){
         System.out.println("Failed to connect to the server at port: "+ portNumber);
         System.out.println("  Exception: "+ e.toString());
     }
     return true;
    }

    // send to an object to the server
    public void send(Object o){
        try {
            System.out.println("Sending an object....");
            this.os.writeObject(o);
            this.os.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // method to received the generic object
    private Object receive(){
        Object o = null;

        try{
            System.out.println(" About to receive an object ");
            o = is.readObject();
            System.out.println(" object received");
        }catch (IOException | ClassNotFoundException e){
            System.out.println("Error occurred on Receiving: "+e.toString());
        }
        return  o;
    }
}
