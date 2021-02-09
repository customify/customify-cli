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
import com.customify.client.data_format.business.GetBusinessFormat;
import com.customify.client.data_format.business.BusinessFormat;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.customify.shared.Response;

import com.customify.shared.responses_data_format.BusinessFormats.BusinessRFormat;
import com.customify.shared.requests_data_formats.BusinessFormats.DeleteBusinessFormat;
import com.customify.shared.responses_data_format.BusinessFormats.BusinessReadFormat;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

public class BusinessService {
    private Socket socket;
    private InputStream input;
    private ObjectInputStream objectInput;
    private String json_data;
    private BusinessReadFormat businessReadFormat;

    public BusinessService () {}

    /**
     * Class Constructor
     * @author IRUMVA HABUMUGISHA Anselme
     * @role Constructor it assigns socket to the variable socket
     * */
    public BusinessService(Socket socket) {
        this.socket = socket;
    }

    /**
     * @author IRUMVA HABUMUGISHA Anselme
     * @param businessFormat the business detains in form of a format
     * @return void
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
     * @param businessFormat the business detains in form of a format
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
        // here I am going to get the data from the server
        InputStream inputStream = this.socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        List<Response> response = (List<Response>) objectInputStream.readObject();


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
    public void getbusinesses (GetBusinessFormat format) throws IOException, ClassNotFoundException{
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

    /**
     * @author Kellia Umuhire
     * @role
     * this function is for handling the response after fetching all the businesses
     * */
    public void handleGetResponse(JsonNode jsonNode) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        businessReadFormat = objectMapper.treeToValue(jsonNode, BusinessReadFormat.class);
        System.out.println("------------------List of Businesses------------------");
        System.out.format("%5s%20s%20s%25s%20s\n","ID","Name","Location","Address","Phone number");
        System.out.println();
        for(BusinessRFormat bs:businessReadFormat.getData()){
                System.out.format("%5d%20s%20s%25s%20s\n",bs.getId(),bs.getName(),bs.getLocation(),bs.getAddress(),bs.getPhone_number());
        }
    }
    /**
     * @author Kellia Umuhire
     * @role
     * method for getting one business by its id
     * */
    public void getById(GetBusinessFormat format) throws IOException,ClassNotFoundException{
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(format);
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
    public void handleGetBYId(JsonNode jsonNode)throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        BusinessRFormat data = objectMapper.treeToValue(jsonNode, BusinessRFormat.class);
        System.out.println("-------------------Business "+data.getId()+"------------------");
        System.out.format("%5s%20s%20s%25s%20s\n","ID","Name","Location","Address","Phone number");
        System.out.println();
        System.out.format("%5d%20s%20s%25s%20s\n",data.getId(),data.getName(),data.getLocation(),data.getAddress(),data.getPhone_number());
    }

    //remove business
    public  void deleteBusiness(DeleteBusinessFormat format) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(format);
        SendToServer serverSend = new SendToServer(json, this.socket);
        if(serverSend.send()){
            System.out.println("--------Business deleted--------");
        }
        else {
            System.out.println("Request failed...");
        }
    }

    /**
     * @author Kellia Umuhire
     * @role
     * General method for handling response from the server
     * */
    public void handleResponse(String func_name) throws ClassNotFoundException{
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
