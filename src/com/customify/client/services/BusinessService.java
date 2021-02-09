/**
 * @description
 * The register business front-end services class this is here to
 * register all the businesses in the project must use this service
 *
 * @author IRUMVA HABUMUGISHA Anselme, Kellia Umuhire
 * @version 1
 * @since Wednesday, 3 February 2021 - 08:17 - Time in Nyabihu
 * */

package com.customify.client.services;

import com.customify.client.SendToServer;
import com.customify.shared.requests_data_formats.BusinessFormats.BusinessFormat;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.customify.shared.Response;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

public class BusinessService {
    private final Socket socket;
    private InputStream input;
    private ObjectInputStream objectInput;
    private String json_data;

    /**
     * @author IRUMVA Anselme
     * @role Constructor it assigns socket to the variable socket
     * */
    public BusinessService(Socket socket) {
        this.socket = socket;
    }

    /**
     * @author IRUMVA HABUMUGISHA Anselme
     * @role
     * this function is to create a new business
     * We send the request to the backend
     * */
    public void create(BusinessFormat businessFormat) throws IOException {
        var mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(businessFormat);
        SendToServer sendToServer = new SendToServer(json, this.socket);
        if (sendToServer.send()) {
            System.out.println("The business is successfully created .... ");
        }else {
            System.out.println("Failed to send the request on the server ....");
        }
    }

    /**
     * @author IRUMVA HABUMUGISHA Anselme
     * @role
     * this function is to edit an existing business
     * We send the request to the backend
     * */
    public void update(BusinessFormat businessFormat) throws IOException {
        var mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(businessFormat);
        SendToServer sendToServer = new SendToServer(json, this.socket);
        if (sendToServer.send()) {
            System.out.println("The business is successfully updated .... ");
        }else {
            System.out.println("The request is not sent to the server ....");
        }
    }

    /**
     * @author IRUMVA HABUMUGISHA Anselme
     * @role
     * this function is to handle response on the successfully creation of the business
     * */
    public void handleCreateBusinessResponse() throws IOException, ClassNotFoundException {
        System.out.println("Creaa 1");
        // here I am going to get the data from the server
        InputStream inputStream = this.socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        List<Response> response = (List<Response>) objectInputStream.readObject();

        System.out.println("Creaa 2") ;
        // if the status code is 201 then I am going to output that The business is created
        if(response.get(0).getStatusCode() == 201){
            System.out.println("The business is created successfully ....");
        }
    }

    /**
     * @author Kellia Umuhire
     * @role
     * this function is for getting all business
     * */
    public void getbusinesses (String json) throws IOException, ClassNotFoundException{
        SendToServer serverSend = new SendToServer(json, this.socket);
        if(serverSend.send()){
//            this.handleResponse("getall");
            this.handleGetResponse();
        }
        else {
            System.out.println("Request failed...");
        }
    }

    /**
     * @author Kellia Umuhire
     * @role
     * this function is for handling the response after fetching all the businesses
     * */
    public void handleGetResponse() throws IOException,ClassNotFoundException{
        this.input = this.socket.getInputStream();
        this.objectInput = new ObjectInputStream(this.input);
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> data = (List<String>) this.objectInput.readObject();
        Iterator itr = data.iterator();
        System.out.println("------------------List of Businesses------------------");
        System.out.format("%5s%20s%20s%20s%20s%20s\n","ID","Name","Location","Address","Phone number","Created_at");
        System.out.println();
        while(itr.hasNext()){
            JsonNode bs = objectMapper.readTree((String) itr.next());
            System.out.format("%5d%20s%20s%20s%20s%20s\n",bs.get("id").asInt(),bs.get("name").asText(),bs.get("location").asText(),bs.get("address").asText(),bs.get("phone_number").asText(),bs.get("created_at").asText());
        }
    }
    /**
     * @author Kellia Umuhire
     * @role
     * method for getting one business by its id
     * */
    public void getById(String json) throws IOException,ClassNotFoundException{
        SendToServer serverSend = new SendToServer(json, this.socket);
        if(serverSend.send()){
            handleResponse("getbyid");
        }
        else {
            System.out.println("Request failed...");
        }
    }

    /**
     * @author Kellia Umuhire
     * @role
     * Method for handling business response from the server
     * */
    public void handleGetBYId(JsonNode jsonNode)throws IOException {
//        if (jsonNode.get("statusCode").asInt() == 400) {
//            System.out.println("Error: "+jsonNode.get("message").asText());
//            return;
//        }
//        if(jsonNode.get("statusCode")==null) {
            System.out.println("-------------------Business " + jsonNode.get("id") + "------------------");
            System.out.format("%5s%20s%20s%20s%20s%20s\n", "ID", "Name", "Location", "Address", "Phone number", "Created_at");
            System.out.println();
            System.out.format("%5d%20s%20s%20s%20s%20s\n", jsonNode.get("id").asInt(), jsonNode.get("name").asText(), jsonNode.get("location").asText(), jsonNode.get("address").asText(), jsonNode.get("phone_number").asText(),jsonNode.get("created_at").asText());
//        }
    }

    /**
     * @author Kellia Umuhire
     * @role
     * Method for sending delete request to the server
     * */
    public  void deleteBusiness(String json) throws IOException,ClassNotFoundException{
        SendToServer serverSend = new SendToServer(json, this.socket);
        if(serverSend.send()){
            this.input = this.socket.getInputStream();
            this.objectInput = new ObjectInputStream(this.input);
            this.json_data = (String)this.objectInput.readObject();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json_data);
            if (jsonNode.get("statusCode").asInt() == 400) {
                System.out.println("Error: "+jsonNode.get("message").asText());
            } else {
                System.out.println("-------------------Business deleted------------------");
            }
        }
        else {
            System.out.println("Request failed...");
        }
    }

    /**
     * @author Kellia Umuhire
     * @role
     * General method for handling response from the server
     * It's not required to use this function for each response
     * */
    public void handleResponse(String func_name) throws IOException,ClassNotFoundException{
        try {
            this.input = this.socket.getInputStream();
            this.objectInput = new ObjectInputStream(this.input);
            this.json_data = (String)this.objectInput.readObject();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json_data);
            switch (func_name){
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
