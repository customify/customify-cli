package com.customify.client.services;

import com.customify.client.SendToServer;
import com.customify.client.data_format.CreateCustomerFormat;
//import com.customify.client.data_format.customer.CreateCustomerFormat;
import com.customify.client.data_format.DisableCustomerFormat;
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
            System.out.println("HERE'S THE RESPONSE FROM THE SERVER => " + json_response);

        }catch(Exception e)
        {
            System.out.println("RESPONSE ERROR =>"+e.getMessage());
        }
    }

    /**
     * @author Murenzi Confiance Tracy
     * @role
     * this function is to handle response on the successfully disabled customer
     * */

    public void update(){}
    public void disable(DisableCustomerFormat format) throws IOException, ClassNotFoundException{
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(format);

        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()){
            // this.handleCreateCustomerResponse();
            System.out.println("\n\n\t\tCard was di-activated successfully\n");
        }
    }
    public void getAll(){}
    public void get(){}




}
