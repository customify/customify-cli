/**
 * @description
 * The register business front-end services class this is here to
 * register all the businesses in the project must use this service
 * register and edit businesses,
 * reading businesses and deleting them.
 *
 * @author IRUMVA HABUMUGISHA Anselme, Kellia Umuhire
 * @version 1
 * @since Wednesday, 3 February 2021 - 08:17 - Time in Nyabihu
 * */

package com.customify.client.services;

import com.customify.client.SendToServer;
import com.customify.client.data_format.business.BusinessFormat;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

public class BusinessService {
    private Socket socket;
    private InputStream input;
    private ObjectInputStream objectInput;
    private String json_data;

    public BusinessService() {
    }

    /**
     * Class Constructor
     *
     * @author IRUMVA HABUMUGISHA Anselme
     * @role Constructor it assigns socket to the variable socket
     */
    public BusinessService(Socket socket) {
        this.socket = socket;
    }

    /**
     * @param businessFormat the business detains in form of a format
     * @return void
     * @author IRUMVA HABUMUGISHA Anselme
     * @role this function is to create a new business
     * We send the request to the backend
     */
    public void create(BusinessFormat businessFormat) throws IOException, ClassNotFoundException {
        var mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(businessFormat);
        SendToServer sendToServer = new SendToServer(json, this.socket);
        if (sendToServer.send()) {
            this.handleResponse("create");
        } else {
            System.out.println("Failed to send the request on the server ....");
        }
    }

    /**
     * @param businessFormat the business detains in form of a format
     * @author IRUMVA HABUMUGISHA Anselme
     * @role this function is to edit an existing business
     * We send the request to the backend
     */
    public void update(BusinessFormat businessFormat) throws IOException, ClassNotFoundException {
        var mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(businessFormat);
        SendToServer sendToServer = new SendToServer(json, this.socket);
        if (sendToServer.send()) {
            this.handleResponse("update");
        } else {
            System.out.println("The request is not sent to the server ....");
        }
    }

    /**
     * @author IRUMVA HABUMUGISHA Anselme
     * @role this function is to handle response on the successfully creation of the business
     */
    public void handleCreateBusinessResponse() throws IOException, ClassNotFoundException {
        // here I am going to get the data from the server
        this.input = this.socket.getInputStream();
        this.objectInput = new ObjectInputStream(this.input);
        String json_data = (String) this.objectInput.readObject();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json_data);
        System.out.println("jjjjjj");
    }

    /**
     * @author Kellia Umuhire
     * @param json Object key to send to the server
     * @role this function is for getting all business
     */
    public void getBusinesses(String json) throws IOException, ClassNotFoundException {
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {
            this.handleGetResponse();
        } else {
            System.out.println("Request failed...");
        }
    }



    /**
     * @author Kellia Umuhire
     * @param json Object holding businessId and key to send to the server
     * @role method for getting one business by its id
     */
    public void getById(String json) throws IOException, ClassNotFoundException {
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {
            handleResponse("getbyid");
        } else {
            System.out.println("Request failed...");
        }
    }

    /**
     * @author Kellia Umuhire
     * @param json Object holding businessId and key to send to the server
     * @role Method for sending a delete request to the server
     */
    public void deleteBusiness(String json) throws IOException, ClassNotFoundException {
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {
            handleResponse("delete_business");
        } else {
            System.out.println("An error occured");
        }
    }

    /**
     * @author Kellia Umuhire
     * @role this function is for handling the response after fetching all the businesses from the server
     * and displaying the response
     */
    public void handleGetResponse() throws IOException, ClassNotFoundException {
        //Get response
        this.input = this.socket.getInputStream();
        this.objectInput = new ObjectInputStream(this.input);
        ObjectMapper objectMapper = new ObjectMapper();

        //Casting the response data to list
        List<String> data = (List<String>) this.objectInput.readObject();
        Iterator itr = data.iterator();

        //display the businesses
        System.out.println("------------------------------------------List of Businesses----------------------------------\n");
        System.out.format("%5s%20s%20s%20s%20s%20s\n", "ID", "Name", "Location", "Address", "Phone number", "Created_at");
        System.out.println();
        while (itr.hasNext()) {
            JsonNode bs = objectMapper.readTree((String) itr.next());
            System.out.format("%5d%20s%20s%20s%20s%20s\n", bs.get("id").asInt(), bs.get("name").asText(), bs.get("location").asText(), bs.get("address").asText(), bs.get("phone_number").asText(), bs.get("created_at").asText());
        }
    }

    /**
     * @author Kellia Umuhire, IRUMVA HABUMUGISHA Anselme
     * @param func_name the name of the function to pass the response to
     * @role General method for handling response from the server
     */
    public void handleResponse(String func_name) throws ClassNotFoundException {
        System.out.println("Received");
        try {
            this.input = this.socket.getInputStream();
            this.objectInput = new ObjectInputStream(this.input);
            this.json_data = (String) this.objectInput.readObject();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json_data);
            System.out.println(jsonNode);
            switch (func_name) {
                case "getall":
                    this.handleGetResponse();
                    break;
                case "getbyid":
                    if(jsonNode.get("statusCode").asInt()==500) System.out.println("An error occured");
                    else{
                        //Display the business
                        System.out.println("-------------------Business " + jsonNode.get("id") + "------------------\n");
                        System.out.format("%5s%20s%20s%20s%20s%20s\n", "ID", "Name", "Location", "Address", "Phone number", "Created_at");
                        System.out.println();
                        System.out.format("%5d%20s%20s%20s%20s%20s\n", jsonNode.get("id").asInt(), jsonNode.get("name").asText(), jsonNode.get("location").asText(), jsonNode.get("address").asText(), jsonNode.get("phone_number").asText(), jsonNode.get("created_at").asText());
                    }
                    break;
                case "create":
                    if (jsonNode.get("status").asInt() == 201) System.out.println("Successfully created a Business");
                    else System.out.println("An error occurred when creating your business with status code of 500");
                    break;
                case "update":
                    if (jsonNode.get("status").asInt() == 200) System.out.println("Successfully updated a Business");
                    else System.out.println("An error occurred when creating your business with status code of 500");
                    break;
                case "delete_business":
                    if (jsonNode.get("statusCode").asInt() == 200)
                        System.out.println("-------------------Business deleted------------------ ");
                    else System.out.println("An error occurred");
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
