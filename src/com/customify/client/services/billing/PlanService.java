package com.customify.client.services.billing;

import com.customify.client.Colors;
import com.customify.client.SendToServer;
import com.customify.client.data_format.billing.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.customify.client.Keys;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class PlanService {
    Socket socket = new Socket();
    InputStream inputStream;
    ObjectInputStream objectInputStream;
    private String response;

    public PlanService(){}
    public PlanService(Socket socket){
        this.socket = socket;
    }

    public void setResponse(String response) {
        this.response = response;
    }
    public String getResponse() {
        return this.response;
    }

    public void createPlan(CreatePlanFormat planFormat) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(planFormat);
        SendToServer sendToServer = new SendToServer(json, socket);
        if(sendToServer.send()){
           this.handleCreatePlanRes();
        }else{
            System.out.println("Request failed");
        }
    }
    public void updatePlan(UpdatePlanFormat planFormat) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(planFormat);
        SendToServer sendToServer = new SendToServer(json, socket);
        if(sendToServer.send()){
           this.handleUpdatePlanRes();
        }else{
            System.out.println("Request failed");
        }
    }
    public void getAllPlan(Keys key) throws Exception{
        GetFeaturesFormat format = new GetFeaturesFormat(Keys.GET_PLANS);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        String json = objectMapper.writeValueAsString(format);
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {
            this.handleGetPlans();
        }
    }

    public void getPlanById(SearchPlanFormat format) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        String json = objectMapper.writeValueAsString(format);
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {
            this.handleGetPlans();
        }
    }

    /**
     * @author Moss
     * @role
     * send request to the server for  deleting a plan
     * */
    public void deletePlan(SearchPlanFormat format) throws IOException, ClassNotFoundException{
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(format);
        SendToServer serverSend = new SendToServer(json, this.socket);
        if(serverSend.send()){
            handleDeleteFeatureRes();
        }
        else {
            System.out.println("\t\t\t\tRequest failed...");
        }
    }

    public void handleGetPlans(){
        /**
         * @author Patrick Niyogitare
         * @role
         * handling the get plans response
         * */
        try{
            InputStream inputStream = this.socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            List<String> response = (List<String>) objectInputStream.readObject();

            System.out.println(Colors.ANSI_CYAN);
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tALL PLANS");
            System.out.println(Colors.ANSI_RESET);
            System.out.format("\t\t\t\t\t\t\t\t\t\t\t\t\t+------------+------------+------------+%n");
            System.out.format("\t\t\t\t\t\t\t\t\t\t\t\t\t| Plan code     | Plan Name |  Plan description | %n");
            System.out.format("\t\t\t\t\t\t\t\t\t\t\t\t\t+------------+------------+------------+%n");
            for(int i=0; i < response.size(); i++) {
                String responseData = response.get(i);
                JsonNode jsonNode = new ObjectMapper().readTree(responseData);
                String leftAlignFormat = "| %-10s | %-10s | %-10s |%n";

                System.out.format("\t\t\t\t\t\t\t\t\t\t\t\t\t" + leftAlignFormat,jsonNode.get("planId").asText() ,jsonNode.get("planName").asText() ,jsonNode.get("planDescription").asText());
                System.out.format("\t\t\t\t\t\t\t\t\t\t\t\t\t+------------+------------+------------+%n");
                System.out.println("\n");
            }
        }catch(Exception e) {
            System.out.println("RESPONSE ERROR =>"+e.getMessage());
        }
    }

    public void handleCreatePlanRes(){
        /**
         * @author Patrick Niyogitare
         * @role
         * handling the create plan response
         * */
        try{
            InputStream inputStream = this.socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            List<String> response = (List<String>) objectInputStream.readObject();

            String json_response = response.get(0);
            JsonNode jsonNode = new ObjectMapper().readTree(json_response);
            if(jsonNode.get("status").asInt() != 201){
                System.out.println("\t\t\t\t An error occured Feature Not created, "+jsonNode.get("status"));
            }else{
                System.out.println("\t\t\t\t Feature successfully created, "+jsonNode.get("status").asInt());
                System.out.println("\n\n");
            }
        }catch(Exception e) {
            System.out.println("RESPONSE ERROR =>"+e.getMessage());
        }
    }


    public void handleDeleteFeatureRes(){
        /**
         * @author Patrick Niyogitare
         * @role
         * handling the delete plan response
         * */
        try{
            InputStream inputStream = this.socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            List<String> response = (List<String>) objectInputStream.readObject();

            String json_response = response.get(0);
            JsonNode jsonNode = new ObjectMapper().readTree(json_response);
            if(jsonNode.get("status").asInt() != 200){
                System.out.println("\t\t\t\t An error occured plan Not deleted, "+jsonNode.get("status"));
            }else{
                System.out.println("\t\t\t\t Plan successfully deleted, "+jsonNode.get("status").asInt());
                System.out.println("\n\n");
            }
        }catch(Exception e) {
            System.out.println("RESPONSE ERROR =>"+e.getMessage());
        }
    }


    public void handleUpdatePlanRes(){
        /**
         * @author Patrick Niyogitare
         * @role
         * handling the updated plan response
         * */
        try{
            InputStream inputStream = this.socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            List<String> response = (List<String>) objectInputStream.readObject();

            String json_response = response.get(0);
            JsonNode jsonNode = new ObjectMapper().readTree(json_response);
            if(jsonNode.get("status").asInt() != 200){
                System.out.println("\t\t\t\t An error occured Plan not updated, "+jsonNode.get("status"));
            }else{
                System.out.println("\t\t\t\t Plan successfully updated, "+jsonNode.get("status").asInt());
                System.out.println("\n\n");
            }
        }catch(Exception e) {
            System.out.println("RESPONSE ERROR =>"+e.getMessage());
        }
    }

}
