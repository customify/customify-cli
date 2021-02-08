package com.customify.client.services;

import com.customify.client.SendToServer;
import com.customify.client.data_format.customer.CreateCustomerFormat;
import com.customify.server.Keys;
import com.customify.shared.Response;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

public class CustomerService {
    private Socket socket;
    public CustomerService(){}

    public  CustomerService(Socket socket)
    {this.socket = socket;}


    public void create(CreateCustomerFormat format) throws IOException, ClassNotFoundException {

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(format);
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {
            this.handleCreateCustomerResponse();
        }

    }


    /**
     * @author SAMUEL Dushimimana
     * @role
     * this function is to handle response on the successfully registration of the customer
     * */
    public void handleCreateCustomerResponse() throws IOException, ClassNotFoundException {


        try {
            InputStream inputStream = this.socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            List<String> response = (List<String>) objectInputStream.readObject();

            String json_response = response.get(0);

            System.out.println("HERE'S THE RESPONSE FROM CLIENT => " + json_response);
        }catch(Exception e)
        {
            System.out.println("RESPONSE ERROR =>"+e.getMessage());
        }
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.readTree(json_data);
//        this.key = Keys.valueOf(jsonNode.get("key").asText());



        // if the status code is 201 then I am going to output that The business is created
//        if(response.get(0).getStatusCode() == 201){
//            System.out.println("The business is created successfully ....");
//        }
    }


    public void update(){}
    public void disable(){}
    public void undisable(){}
    public void getAll(){}
    public void get(){}




}
