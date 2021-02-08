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

import com.customify.client.Common;
import com.customify.client.SendToServer;
import com.customify.client.data_format.business.GetBusinessFormat;
import com.customify.shared.requests_data_formats.BusinessFormats.BusinessFormat;

import com.customify.shared.Keys;
import com.customify.shared.Request;
import com.customify.shared.Response;

import com.customify.shared.responses_data_format.BusinessFormats.BusinessRFormat;
import com.customify.shared.requests_data_formats.BusinessFormats.DeleteBusinessFormat;
import com.customify.shared.responses_data_format.BusinessFormats.BusinessReadFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    private BusinessReadFormat businessReadFormat;

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
    public void create(BusinessFormat businessFormat) throws IOException, ClassNotFoundException {
        // make my request
        Request request = new Request(Keys.CREATE_BUSINESS , businessFormat);

        // Make the Backend connector
        Common common = new Common(request,this.socket);

        // send to the server
        if(common.sendToServer()){
            this.handleCreateBusinessResponse();
        }else{
            System.out.println("There was an error when sending request ....");
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
    public void getbusinesses (GetBusinessFormat format) throws IOException, ClassNotFoundException{
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(format);
        SendToServer serverSend = new SendToServer(json, this.socket);
        if(serverSend.send()){
            this.handleResponse("getall");
        }
    }

    /**
     * @author Kellia Umuhire
     * @role
     * this function is for handling the response after fetching all the businesses
     * */
    public void handleGetResponse(JsonNode jsonNode) throws IOException,ClassNotFoundException{
        ObjectMapper objectMapper = new ObjectMapper();
        businessReadFormat = objectMapper.treeToValue(jsonNode, BusinessReadFormat.class);
        System.out.println("------------------List of Businesses------------------");
        System.out.format("%5s%20s%20s%25s%20s\n","ID","Name","Location","Address","Phone number");
        System.out.println();
        for(BusinessRFormat bs:businessReadFormat.getData()){
                System.out.format("%5d%20s%20s%25s%20s\n",bs.getId(),bs.getName(),bs.getLocation(),bs.getAddress(),bs.getPhone_number());
        }
    }

    //get business by id
    public void getById(GetBusinessFormat format) throws IOException,ClassNotFoundException{
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(format);
        SendToServer serverSend = new SendToServer(json, this.socket);
        if(serverSend.send()){
            handleResponse("getbyid");
        }
    }

    //handle get business
    public void handleGetBYId(JsonNode jsonNode)throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        BusinessRFormat data = objectMapper.treeToValue(jsonNode, BusinessRFormat.class);
        System.out.println("-------------------Business "+data.getId()+"------------------");
        System.out.format("%5s%20s%20s%25s%20s\n","ID","Name","Location","Address","Phone number");
        System.out.println();
        System.out.format("%5d%20s%20s%25s%20s\n",data.getId(),data.getName(),data.getLocation(),data.getAddress(),data.getPhone_number());
    }

    //remove business
    public  void deleteBusiness(DeleteBusinessFormat format) throws IOException,ClassNotFoundException{
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(format);
        SendToServer serverSend = new SendToServer(json, this.socket);
        if(serverSend.send()){
            System.out.println("--------Business deleted--------");
        }
    }

    //handle response
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
