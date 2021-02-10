package com.customify.client.services;

import com.customify.client.Common;
import com.customify.client.SendToServer;
import com.customify.client.points.GetWinners;
import com.customify.client.points.PointsByEmail;
import com.customify.shared.Request;
import com.customify.shared.Response;
import com.customify.shared.responses_data_format.WinnersDataFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.customify.client.Keys;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

/**
 * @author GISA KAZE Fredson
 * */

public class PointsService {
    private Socket socket;
    private InputStream input;
    private ObjectInputStream objectInput;

    public PointsService() {
    }

    public PointsService(Socket socket) throws IOException {
        this.socket = socket;
    }

    /**
     * @author GISA KAZE Fredson
     */
    public void getWinners() {

        try {
            GetWinners format = new GetWinners(Keys.GET_WINNERS);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(format);
            SendToServer serverSend = new SendToServer(json, this.socket);
            if (serverSend.send()) {
                System.out.println("\n\t\tRequest Sent successfully\n");
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

    public void getPointsByCustomerEmail(String email) {
        try {
//            System.out.println(format);
//            Request request = new Request(Keys.POINTS_BY_CUSTOMER_EMAIL, format);
//            this.common = new Common(request, this.socket);

//            if (common.sendToServer()) {
//                System.out.println("\n\t\t   Request Sent successfully\n");
//            } else {
//                System.out.println("\nError occurred when sending request to server\n");
//            }

            PointsByEmail format = new PointsByEmail(Keys.POINTS_BY_CUSTOMER_EMAIL, email);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(format);
            SendToServer serverSend = new SendToServer(json, this.socket);
            if (serverSend.send()) {
                System.out.println("\n\t\t   Request Sent successfully\n");
                this.getAllWinnersSuccess();
            }
            else{
                System.out.println("\nError occurred when sending request to server\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllWinnersSuccess() throws IOException {
        this.input = this.socket.getInputStream();
        this.objectInput = new ObjectInputStream(this.input);
        System.out.println("out of time");
        try {
            List<String> clientRequest = (List)this.objectInput.readObject();
            if(clientRequest.size() == 0){
                System.out.println("We don't have any winner.");
            }
            else{
                for(int i =0; i< clientRequest.size(); i++){
                    String json_data = (String)clientRequest.get(i);
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode winners = objectMapper.readTree(json_data);
                    System.out.println(String.format("\t\t%-10s %-25s %-15s %-20s %14s %23s %22s", winners.get("customerId").asText(), winners.get("firstName").asText(), winners.get("lastName").asText(), winners.get("email").asText(), winners.get("noPoints").asDouble(), winners.get("winningDate").asText(), winners.get("code").asInt()));

                }
            }



//            if(response.get(0).getStatusCode() == 200){
//                List<WinnersDataFormat> winners =(List<WinnersDataFormat>) response.get(0).getData();
//
//                if (winners.size() == 0) {
//                    return;
//                }
//
//                System.out.println(String.format("\t\t%-10s %-25s %-15s %-20s %17s %20s %25s", "Customer Id", "First name", "Last name", "Email", "Points", "Winning date", "Customer code"));
//
//                for (int i = 0; i < winners.size(); i++) {
//                    System.out.println(String.format("\t\t%-10s %-25s %-15s %-20s %14s %23s %22s", winners.get(i).getCustomerId(), winners.get(i).getFirstName(), winners.get(i).getLastName(), winners.get(i).getEmail(), winners.get(i).getNoPoints(), winners.get(i).getWiningDate(), winners.get(i).getCode()));
//                }
//            }
//            else{
//                System.out.println("\n Unknown error occurred.Check your internet connection \n");
//            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Sending........");
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}