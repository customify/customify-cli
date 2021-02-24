package com.customify.client.services;

import com.customify.client.Keys;
import com.customify.client.SendToServer;
import com.customify.client.data_format.CreateCustomerFormat;
//import com.customify.client.data_format.customer.CreateCustomerFormat;
import com.customify.client.data_format.DeActivateCustomerFormat;
import com.customify.client.data_format.DisableCustomerFormat;
import com.customify.client.data_format.UpdateCustomerFormat;
import com.customify.client.data_format.products.ProductFormat;
//import com.customify.shared.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
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

    public Socket getSocket() {
        return socket;
    }
    /**
     * @author Nshimiye Emmy
     * @role
     * this service method is to update the customer on client side
     * */
    public void handleUpdateCustomerSuccess() throws IOException, ClassNotFoundException {
        InputStream inputStream = this.getSocket().getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
//        try {
//            List<Response> response = (List<Response>) objectInputStream.readObject();
//            System.out.println("Status: "+ response.get(0).getStatusCode());
//            if(response.get(0).getStatusCode() == 200){
//                UpdateCustomerFormat updatedCustomer = (UpdateCustomerFormat) response.get(0).getData();
//
//                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
//                System.out.println("\t\t Customer Updated successfully");
//                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
//            }
//            else if(response.get(0).getStatusCode() == 400){
//                System.out.println("\n\nInvalid Customer format.Please enter Customer details as required\n\n");
//            }
//            else{
//                System.out.println("\n\nUnknown error occurred.\n");
//            }
//
//        } catch (IOException e) {
//            System.out.println("\n\nError occurred:" +e.getMessage()+ "\n\n");
//        } catch (ClassNotFoundException e) {
//            System.out.println("\n\nError occurred:" +e.getMessage()+ "\n\n");
//        }

        return;
    }

    /**
     * @author Nshimiye Emmy
     * @role
     * this service method is to update the customer on client side
     * */
    public void update(UpdateCustomerFormat format) throws IOException, ClassNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(format);
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {
            this.handleUpdateCustomerSuccess();
        }
        else{
            System.out.println("\n\nError occurred when trying to send request to server\n");
        }

    }


    /**
     * @author Murenzi Confiance Tracy
     * @role
     * this function is to handle response on the successfully disabled customer
     * */
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

    public void reEnableCard(String code) throws IOException {
        DeActivateCustomerFormat format = new DeActivateCustomerFormat(Keys.RENABLE_CUSTOMER,code);
        ObjectMapper objectMapper = new ObjectMapper();
        String request = objectMapper.writeValueAsString(format);
        SendToServer sendToServer = new SendToServer(request,socket);

        if (sendToServer.send()){
            System.out.println("\t\tUser activated successfully");
        }
    }
}
