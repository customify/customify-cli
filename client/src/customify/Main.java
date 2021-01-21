package customify;
import java.io.*;
import java.net.*;

public class Main {
//    private ObjectOutput os = null;
//    private ObjectInputStream is = null;

      private DataInputStream input = null;
      private DataOutputStream output = null;

    public Main(String serverIP){
        System.out.println(serverIP);
        if(!connectTOServer(serverIP)){
            System.out.println("Failed to connect to the server to: "+serverIP);
        }
    }

    public static void main(String[] args){
        System.out.println("Customify  is starting..........");
        Main theApp = new Main("localhost");
        System.out.println("End..........1......");
    }

    private boolean connectTOServer(String serverIp){
        int portNumber = 3000;
        try{

            Socket socket = new Socket(serverIp, portNumber);
//            this.os = new ObjectOutputStream(socket.getOutputStream());
//            this.is = new ObjectInputStream(socket.getInputStream());

              this.input = new DataInputStream(socket.getInputStream());
              this.output = new DataOutputStream(socket.getOutputStream());

            System.out.println("Connected to the server "+ socket.getInetAddress() + " on port "+ socket.getPort());
            System.out.println("from local Address: "+ socket.getLocalAddress()+" and port "+ socket.getLocalPort());


            //start communicatio with server
            String query = "";
            while(!query.equals("exit")){
                query = this.readFromConsole();
                this.output.writeUTF(query);
                System.out.println(this.input.readUTF());
            }

        }catch (Exception e){
            System.out.println("Failed to connect to the server at port: "+ portNumber);
            System.out.println("  Exception: "+ e.toString());
        }
        return true;
    }


    // send to an object to the server
//    public void send(Object o){
//        try {
//            System.out.println("Sending an object....");
//            this.os.writeObject(o);
//            this.os.flush();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }

    // method to received the generic object
//    private Object receive() throws IOException {
////        Object o = null;
//
//
//        try{
//            System.out.println(" About to receive an object ");
//            o = input.readUTF();
//            System.out.println(" object received... "+o);
//        }catch (IOException e){
//            System.out.println("Error occurred on Receiving: "+e.toString());
//        }
//        return  o;
//    }
//}


//--------------------  read request from console ------------
private String readFromConsole() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter dummy request: ");
        String userInput = reader.readLine();
        return userInput;
    }
}
