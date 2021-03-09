/**
 * @description
 * server-side feature service for handling operations related to the billing feature
 *
 * @author fiston nshimiyandinze
 * @since Wednesday, 5 February 2021
 * */
package com.customify.client.services;

import com.customify.client.Colors;
import com.customify.client.Keys;
import com.customify.client.SendToServer;
import com.customify.client.data_format.BillingFeature.GetFeatureFormat;

import com.customify.client.data_format.billing.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;



public class FeatureService {
    private final Socket socket;
    private InputStream input;
    private ObjectInputStream objectInput;
    private String json_data;
    public FeatureService(Socket socket){
        this.socket = socket;

    }

    /**
     * @author fiston nshimiyandinze
     * @role
     * send request to the server for registering given feature into database
     * */

    public  void RegisterFeature(FeatureFormat featureFormat) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(featureFormat);
        SendToServer sendToServer = new SendToServer(json, this.socket);
        if (sendToServer.send()) {
            handleCreateFeature();
        }else {
            System.out.println("Failed to send the request on the server ....");
        }
    }

    /**
     * @author fiston nshimiyandinze
     * send request to the server for updating  feature in database
     * */
    public void update(UpdatedFeatureFormat featureFormat) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(featureFormat);
        SendToServer sendToServer = new SendToServer(json, this.socket);
        if (sendToServer.send()) {
            System.out.println("\t\t\t\tThe feature is successfully updated .... ");
            handleUpdateFeatureResponse();
        }else {
            System.out.println("\t\t\t\tThe request is not sent to the server ....");
        }
    }


    /**
     * @author fiston nshimiyandinze
     * @role
     * send request to the server for  get all features from the database
     * and sending back the response
     * */
    public void getFeatures() throws IOException, ClassNotFoundException{
        GetFeaturesFormat format = new GetFeaturesFormat(Keys.GET_FEATURES);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        String json = objectMapper.writeValueAsString(format);
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {
            this.handleGetFeaturesRes();
        }
    }

    /**
     * @author fiston nshimiyandinze
     * @role
     * send request to the server for getting feature matching given  id  from the database
     * */
    public void getFeaturesById(GetFeatureByIdFormat format) throws IOException, ClassNotFoundException{
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(format);
        SendToServer serverSend = new SendToServer(json, this.socket);
        if(serverSend.send()){
            handleGetFeaturesRes();
        }
        else {
            System.out.println("Request failed...");
        }
    }
//
    /**
     * @author fiston nshimiyandinze
     * @role
     * send request to the server for  deleting feature matching given id from the database
     * */
    public void deleteFeature(DeleteFeatureFormat format) throws IOException, ClassNotFoundException{
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

    public void handleGetFeaturesRes(){
        /**
         * @author Patrick Niyogitare
         * @role
         * handling the get features response
         * */
        try{
            InputStream inputStream = this.socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            List<String> response = (List<String>) objectInputStream.readObject();

            System.out.println(Colors.ANSI_CYAN);
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tALL FEATURES");
            System.out.println(Colors.ANSI_RESET);
            System.out.format("\t\t\t\t\t\t\t\t\t\t\t\t\t+------------+------------+------------+%n");
            System.out.format("\t\t\t\t\t\t\t\t\t\t\t\t\t| Feature code     | Feature Name |  Feature description | %n");
            System.out.format("\t\t\t\t\t\t\t\t\t\t\t\t\t+------------+------------+------------+%n");

            for(int i=0; i < response.size(); i++) {
                String responseData = response.get(i);
                JsonNode jsonNode = new ObjectMapper().readTree(responseData);
                String leftAlignFormat = "| %-10s | %-10s | %-10s |%n";

                System.out.format("\t\t\t\t\t\t\t\t\t\t\t\t\t" + leftAlignFormat,jsonNode.get("featureCode").asText() ,jsonNode.get("featureName").asText() ,jsonNode.get("featureDescription").asText());
                System.out.format("\t\t\t\t\t\t\t\t\t\t\t\t\t+------------+------------+------------+%n");
                System.out.println("\n");
            }
        }catch(Exception e) {
            System.out.println("RESPONSE ERROR =>"+e.getMessage());
        }
    }


    public void handleCreateFeature(){
        /**
         * @author Patrick Niyogitare
         * @role
         * handling the create feature response
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
         * handling the delete feature response
         * */
        try{
            InputStream inputStream = this.socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            List<String> response = (List<String>) objectInputStream.readObject();

            String json_response = response.get(0);
            JsonNode jsonNode = new ObjectMapper().readTree(json_response);
            if(jsonNode.get("status").asInt() != 200){
                System.out.println("\t\t\t\t An error occured Feature Not deleted, "+jsonNode.get("status"));
            }else{
                System.out.println("\t\t\t\t Feature successfully deleted, "+jsonNode.get("status").asInt());
                System.out.println("\n\n");
            }
        }catch(Exception e) {
            System.out.println("RESPONSE ERROR =>"+e.getMessage());
        }
    }

    public void handleUpdateFeatureResponse(){
        /**
         * @author Patrick Niyogitare
         * @role
         * handling the delete feature response
         * */
        try{
            InputStream inputStream = this.socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            List<String> response = (List<String>) objectInputStream.readObject();

            String json_response = response.get(0);
            JsonNode jsonNode = new ObjectMapper().readTree(json_response);
            if(jsonNode.get("status").asInt() != 200){
                System.out.println("\t\t\t\t An error occured Feature Not updated, "+jsonNode.get("status"));
            }else{
                System.out.println("\t\t\t\t Feature successfully updated, "+jsonNode.get("status").asInt());
                System.out.println("\n\n");
            }
        }catch(Exception e) {
            System.out.println("RESPONSE ERROR =>"+e.getMessage());
        }
    }


}
