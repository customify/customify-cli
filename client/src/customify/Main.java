package customify;

import java.io.*;
import java.net.*;
import java.util.*;

public class Main {

    private DataOutputStream output = null;
    private boolean isConnectionOn = true;

    public Main(String serverIP){
        System.out.println(serverIP);
        if(!connectTOServer(serverIP)){
            System.out.println("Failed to connect to the server to: "+serverIP);
        }
    }

    public static void main(String[] args){
        System.out.println("Customify  is starting..........");
        new Main("localhost");
        System.out.println("Ending................");
    }

    private boolean connectTOServer(String serverIp){
        int portNumber = 3000;
        try{

            Socket socket = new Socket(serverIp, portNumber);

          //  DataInputStream input = new DataInputStream(socket.getInputStream());
              this.output = new DataOutputStream(socket.getOutputStream());

            System.out.println("Connected to the server "+ socket.getInetAddress() + " on port "+ socket.getPort());
            System.out.println("from local Address: "+ socket.getLocalAddress()+" and port "+ socket.getLocalPort());


            List<Object> query = null;

            while(isConnectionOn){
                query.addAll(Objects.requireNonNull(this.readFromConsole()));
                //this.output.writeUTF(String.valueOf(query));
                this.sendToServer(query);
                //System.out.println(this.input.readUTF());
            }

        }catch (Exception e){
            this.isConnectionOn = false;
            System.out.println("Failed to connect to the server at port: "+ portNumber);
            System.out.println("  Exception: "+ e.toString());
        }
        return true;
    }

     //--------------------  read request from console ------------
     private List<Object> readFromConsole() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Object> dataFromUser = null;

        try{
            System.out.println("Enter key request: ");
            dataFromUser.add(reader.readLine());

            System.out.println("Enter data: ");
            dataFromUser.add(reader.readLine());
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return dataFromUser;
     }

     public void sendToServer(List<Object> dataToSend) throws IOException {
           this.output.writeUTF(String.valueOf(dataToSend));
     }
}