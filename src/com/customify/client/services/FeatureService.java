package com.customify.client.services;

import com.customify.client.SendToServer;
import com.customify.client.data_format.BillingFeature.GetFeatureFormat;
import com.customify.shared.requests_data_formats.BillingFeature.FeatureCode;
import com.customify.shared.requests_data_formats.BillingFeature.FeatureFormat;
import com.customify.shared.responses_data_format.BillingFeature.FeatureReadFormat;
import com.customify.shared.responses_data_format.BillingFeature.MessageFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    public  void RegisterFeature(FeatureFormat featureFormat) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(featureFormat);
        SendToServer sendToServer = new SendToServer(json, this.socket);
        if (sendToServer.send()) {
            System.out.println("The feature is successfully created .... ");
        }else {
            System.out.println("Failed to send the request on the server ....");
        }
    }
    public void update(FeatureFormat featureFormat) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(featureFormat);
        SendToServer sendToServer = new SendToServer(json, this.socket);
        if (sendToServer.send()) {
            System.out.println("The feature is successfully updated .... ");
        }else {
            System.out.println("The request is not sent to the server ....");
        }
    }



    public void getFeatures(GetFeatureFormat format) throws IOException, ClassNotFoundException{
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(format);
        SendToServer serverSend = new SendToServer(json, this.socket);
        if(serverSend.send()){
            this.handleResponse("getAll");
        }
        else {
            System.out.println("Request failed...");
        }
    }


    public void getFeaturesById(FeatureCode format) throws IOException, ClassNotFoundException{
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(format);
        SendToServer serverSend = new SendToServer(json, this.socket);
        if(serverSend.send()){
            this.handleResponse("getById");
        }
        else {
            System.out.println("Request failed...");
        }
    }


    public void deleteFeature(FeatureCode format) throws IOException, ClassNotFoundException{
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(format);
        SendToServer serverSend = new SendToServer(json, this.socket);
        if(serverSend.send()){
            this.handleResponse("getById");
        }
    }


    public void handleResponse(String func_name) throws IOException,ClassNotFoundException{
        try {
            this.input = this.socket.getInputStream();
            this.objectInput = new ObjectInputStream(this.input);
            this.json_data = (String)this.objectInput.readObject();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json_data);
            switch (func_name){
                case "getAll":
                    this.handleGetResponse(jsonNode);
                    break;
                case "getById":
                    this.handleGetBYId(jsonNode);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } catch (IOException e) {
            System.out.println("Error in reading Object " + e.getMessage());
        }

            }



    public void handleGetBYId(JsonNode jsonNode)throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        FeatureFormat data = objectMapper.treeToValue(jsonNode, FeatureFormat.class);
        System.out.println("-------------------Feature------------------------------");
        System.out.format("%5s%20s%20s%25s%20s\n","FeatureCode","Name","Description");
        System.out.println();
        System.out.format("%5d%20s%20s%25s%20s\n",data.getFeatureCode(),data.getFeatureName(),data.getFeatureDescription());
    }

    public void handleGetResponse(JsonNode jsonNode) throws IOException,ClassNotFoundException{
        ObjectMapper objectMapper = new ObjectMapper();
        FeatureReadFormat featureReadFormat = objectMapper.treeToValue(jsonNode, FeatureReadFormat.class);
        System.out.println("-------------------Feature------------------------------");
        System.out.format("%5s%20s%20s%25s%20s\n","FeatureCode","Name","Description");
        System.out.println();
        for(FeatureFormat bs:featureReadFormat.getData()){
            System.out.format("%5d%20s%20s%25s%20s\n",bs.getFeatureCode(),bs.getFeatureName(),bs.getFeatureDescription());
        }
    }

    public void handleCreateFeature()  {
        try {
            InputStream inputStream = this.socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            List<String> response = (List<String>) objectInputStream.readObject();

            String json_response = response.get(0);
            System.out.println("HERE'S THE RESPONSE FROM THE SERVER => " + json_response);

        }catch(Exception e)
        {
            System.out.println("RESPONSE ERROR =>"+e.getMessage());
        }
    }
}
