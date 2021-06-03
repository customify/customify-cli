/**
 * @author GISA KAZE Fredson
 * */

package com.customify.client.services;

import com.customify.client.Colors;
import com.customify.client.SendToServer;
import com.customify.client.data_format.GetWinnersDataFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class PointsService {
    private Socket socket;
//    private Common common;
    InputStream inputStream;
    ObjectInputStream objectInputStream;

    public PointsService(){

    }

    public PointsService(Socket socket) {
        this.socket = socket;
    }

    public void getWinners() {
        try {
            GetWinnersDataFormat data =  new GetWinnersDataFormat();
            ObjectMapper objectMapper = new ObjectMapper();

            String request = objectMapper.writeValueAsString(data);

            SendToServer sendToServer = new SendToServer(request,socket);
            if (sendToServer.send()) {
//                System.out.println("\n\t\tRequest Sent successfully\n");
                this.getAllWinnersSuccess();
            }
            else{
                System.out.println("\nError occurred when sending request to server\n");
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
//        System.out.println("finished sending");
    }

//    public void getPointsByCustomerEmail(PointsByCustomerEmailFormat format) {
//        try {
////                System.out.println(format);
//            Request request = new Request(Keys.POINTS_BY_CUSTOMER_EMAIL, format);
//            this.common = new Common(request, this.socket);
//            if (common.sendToServer()) {
//                System.out.println("\n\t\t   Request Sent successfully\n");
//            } else {
//                System.out.println("\nError occurred when sending request to server\n");
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }

    public void getAllWinnersSuccess() throws IOException {

        inputStream = this.getSocket().getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);
        ObjectMapper objectMapper = new ObjectMapper();



        try {
            List<String> response = (ArrayList<String>) objectInputStream.readObject();
//            System.out.println(response);
//            List<Response> response = (List<Response>) objectInputStream.readObject();

//            if(response.get(0).getStatusCode() == 200){
//                List<WinnersDataFormat> winners =(List<WinnersDataFormat>) response.get(0).getData();
//
//                if (winners.size() == 0) {
//                    System.out.println("We don't have any winner.");
//                    return;
//                }

            if(response.size() == 0 ) {
                System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\tNo winner to show\n\n");
                return;
            }
            System.out.println("\n\n");
            this.Header();
            System.out.println(Colors.ANSI_GREEN);
            System.out.format("\t%-15s%-25s%-25s%-30s%-10s%-30s%-10s", "Customer Id", "First name", "Last name", "Email", "Points", "Winning date", "Customer code");
            System.out.println(Colors.ANSI_RESET);
            for (int i = 0; i < response.size(); i++) {
                JsonNode node = objectMapper.readTree(response.get(i));

                System.out.format("\t%-15s%-25s%-25s%-30s%-10s%-30s%-10s\n", node.get("customerId").asText(),node.get("firstName").asText(), node.get("lastName").asText(), node.get("email").asText(), node.get("noPoints").asDouble(), node.get("winingDate").asText(), node.get("code").asText());
//                System.out.println("\n");
            }
            System.out.println("\n\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    public void Header(){
        System.out.println(Colors.ANSI_CYAN);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWINNERS");
        System.out.println(Colors.ANSI_RESET);
    }
}