package customify.client;


import customify.Request;

import java.io.*;
import java.net.*;
import java.util.*;

public class Main {

    private OutputStream output = null;
    private ObjectOutputStream objectOutput = null;
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
            this.output = socket.getOutputStream();
            this.objectOutput = new ObjectOutputStream(output);

            System.out.println("Connected to the server "+ socket.getInetAddress() + " on port "+ socket.getPort());
            System.out.println("from local Address: "+ socket.getLocalAddress()+" and port "+ socket.getLocalPort());


            List<Object> query = new ArrayList<Object>();
            while(isConnectionOn){
                this.sendToServer(this.readFromConsole());
            }

        }catch (Exception e){
            this.isConnectionOn = false;
            System.out.println("Failed to connect to the server at port: "+ portNumber);
            System.out.println("  Exception: "+ e.toString());
        }
        return true;
    }

    //--------------------  read request from console ------------
    private List<Request> readFromConsole() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Request> dataFromUser = new ArrayList<>();
        String key;
        String userInput;

        try{
            System.out.println("Enter key request: ");
            key = reader.readLine();

            System.out.println("Enter data: ");
            userInput = reader.readLine();

            Request request = new Request(key,userInput);
            dataFromUser.add(request);

        }catch (NullPointerException e){
            e.printStackTrace();
        }

        for(int i = 0; i < dataFromUser.size(); i++){
            Request req = (Request)dataFromUser.get(i);
            System.out.println("Key: "+req.getKey()+" value: "+req.getRequestData());
        }
        return dataFromUser;
    }

    public void sendToServer(List<Request> dataToSend) throws IOException {
        for(int i = 0; i < dataToSend.size(); i++){
            Request req = (Request)dataToSend.get(i);
        }
        this.objectOutput.writeObject(dataToSend);
    }
}

