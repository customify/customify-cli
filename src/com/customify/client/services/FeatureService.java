package com.customify.client.services;

import com.customify.client.SendToServer;
import com.customify.client.data_format.BillingFeature.GetFeatureFormat;
import com.customify.shared.requests_data_formats.BillingFeature.FeatureFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

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

//    public void handleCreateBusinessResponse() throws IOException, ClassNotFoundException {
//        System.out.println("Creaa 1");
//        // here I am going to get the data from the server
//        InputStream inputStream = this.socket.getInputStream();
//        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
//        List<Response> response = (List<Response>) objectInputStream.readObject();
//
//        System.out.println("Creaa 2") ;
//        // if the status code is 201 then I am going to output that The business is created
//        if(response.get(0).getStatusCode() == 201){
//            System.out.println("The business is created successfully ....");
//        }
//    }


    public void getfeatures (GetFeatureFormat format) throws IOException, ClassNotFoundException{
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(format);
        SendToServer serverSend = new SendToServer(json, this.socket);
        if(serverSend.send()){
            this.handleResponse("getall");
        }
        else {
            System.out.println("Request failed...");
        }
    }


    public void handleGetBYid(JsonNode jsonNode)throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        BusinessRFormat data = objectMapper.treeToValue(jsonNode, BusinessRFormat.class);
        System.out.println("-------------------Business "+data.getId()+"------------------");
        System.out.format("%5s%20s%20s%25s%20s\n","ID","Name","Location","Address","Phone number");
        System.out.println();
        System.out.format("%5d%20s%20s%25s%20s\n",data.getId(),data.getName(),data.getLocation(),data.getAddress(),data.getPhone_number());
    }


    public void handleResponse(String func_name) throws IOException,ClassNotFoundException{
        try {
            this.input = this.socket.getInputStream();
            this.objectInput = new ObjectInputStream(this.input);
            this.json_data = (String)this.objectInput.readObject();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json_data);
            switch (func_name){
                case "getall":
                    this.handleGetResponse(jsonNode);
                    break;
                case "getbyid":
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

}
