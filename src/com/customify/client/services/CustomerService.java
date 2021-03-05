package com.customify.client.services;

import com.customify.client.Colors;
import com.customify.client.Keys;
import com.customify.client.SendToServer;
import com.customify.client.data_format.*;
//import com.customify.client.data_format.customer.CreateCustomerFormat;
import com.customify.client.data_format.products.ProductFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private Socket socket;
    private ObjectMapper objectMapper;

    public CustomerService() {
    }

    public CustomerService(Socket socket) {
        this.socket = socket;
    }


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
     * @role this function is to handle response on the successfully registration of the customer
     */
    public void handleCreateCustomerResponse() throws IOException, ClassNotFoundException {
        try {
            InputStream inputStream = this.socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            List<String> response = (List<String>) objectInputStream.readObject();

            String json_response = response.get(0);
            System.out.println("HERE'S THE RESPONSE FROM THE SERVER => " + json_response);

        } catch (Exception e) {
            System.out.println("RESPONSE ERROR =>" + e.getMessage());
        }
    }

    public Socket getSocket() {
        return socket;
    }

    /**
     * @author Nshimiye Emmy
     * @role this service method is to update the customer on client side
     */
    public void handleUpdateCustomerSuccess() throws IOException, ClassNotFoundException {
        InputStream inputStream = this.getSocket().getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
//        InputStream inputStream = this.getSocket().getInputStream();
//        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
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
     * @role this service method is to update the customer on client side
     */
    public void update(UpdateCustomerFormat format) throws IOException, ClassNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(format);
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {
            this.handleUpdateCustomerSuccess();
        } else {
            System.out.println("\n\nError occurred when trying to send request to server\n");
        }
    }


    /**
     * @author Murenzi Confiance Tracy
     * @role this function is to handle response on the successfully disabled customer
     */
    public void disable(String json) throws IOException, ClassNotFoundException {
        try {

            SendToServer serverSend = new SendToServer(json, this.socket);
            if (serverSend.send()) {
                InputStream input = this.socket.getInputStream();
                ObjectInputStream objectInput = new ObjectInputStream(input);
                List<String> res = (List) objectInput.readObject();
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(res.get(0));

                if (jsonNode.get("status").asInt() == 200) {
                    System.out.println(Colors.ANSI_PURPLE);
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t          CARD WAS SUCCESSFULLY DE-ACTIVATED!!!!");
                    System.out.println(Colors.ANSI_RESET);
                } else if (jsonNode.get("status").asInt() == 404) {
                    System.out.println(Colors.ANSI_PURPLE);
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t        THE CUSTOMER DOESN'T EXIST");
                    System.out.println(Colors.ANSI_RESET);
                } else if (jsonNode.get("status").asInt() == 444) {
                    System.out.println(Colors.ANSI_PURPLE);
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t        NO RESPONSE");
                    System.out.println(Colors.ANSI_RESET);
                }
                else {
                    System.out.println(Colors.ANSI_PURPLE);
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t        UNKNOWN CODE");
                    System.out.println(Colors.ANSI_RESET);
                }
            }

        } catch (Exception e) {
            System.out.println("Exception Caught ");
        }
        return;
    }

    /**
     * @author Murenzi Confiance Tracy
     * @role this function is to handle response on the successfully activated customer
     */

    public Object reEnableCard(String code) throws IOException, ClassNotFoundException {
        DeActivateCustomer format = new DeActivateCustomer(code);
        ObjectMapper objectMapper = new ObjectMapper();
        String request = objectMapper.writeValueAsString(format);
        SendToServer sendToServer = new SendToServer(request, socket);

        if (sendToServer.send()) {
//                System.out.println("\t\tCard was activated successfully\n");
            InputStream input = this.socket.getInputStream();
            ObjectInputStream objectInput = new ObjectInputStream(input);
            List<String> res = (List<String>) objectInput.readObject();

            String responseData = res.get(0);
//            System.out.println("Response: " + responseData);
            JsonNode jsonNode = new ObjectMapper().readTree(responseData);

            if (jsonNode.get("status").asInt() == 200) {
                System.out.println(Colors.ANSI_PURPLE);
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t          CARD WAS SUCCESSFULLY ACTIVATED!!!!");
                System.out.println(Colors.ANSI_RESET);
            } else if (jsonNode.get("status").asInt() == 400) {
                System.out.println(Colors.ANSI_PURPLE);
                System.out.println("\t\t\t\t\t\t\t\t\t\t THE CUSTOMER DOESN'T EXIST");
                System.out.println(Colors.ANSI_RESET);
            } else if (jsonNode.get("status").asInt() == 500) {
                System.out.println(Colors.ANSI_PURPLE);
                System.out.println("\t\t\t\t\t\t\t\t\t\t  SYSTEM ERROR OCCURRED");
                System.out.println(Colors.ANSI_RESET);
            } else {
                System.out.println(Colors.ANSI_PURPLE);
                System.out.println("\t\t\t\t\t\t\t\t\t\t   UNKNOWN ERROR");
                System.out.println(Colors.ANSI_RESET);
            }
        }
        return null;
    }

    public List getAll() throws IOException {
        String json = "{ \"key\" : \"" + Keys.GET_ALL_CUSTOMERS + "\"}";
        SendToServer serverSend = new SendToServer(json, this.socket);
        List<String> res = new ArrayList<>();
        if (serverSend.send()) {

            try {
                InputStream input = this.socket.getInputStream();
                ObjectInputStream objectInput = new ObjectInputStream(input);
                res = (List) objectInput.readObject();

                objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(res.get(0));

                if (jsonNode.get("status").asInt() == 500) {
                    System.out.println("\t\t\t\t---- INTERNAL SERVER ERROR -----");
                    return null;
                } else if (jsonNode.get("status").asInt() == 404) {
                    System.out.println("\n\t\t\t*******************************************************************************************************");
                    System.out.println("                                                 NO CUSTOMERS FOUND                                            ");
                    System.out.println("\t\t\t*******************************************************************************************************");
                    return null;
                }


            } catch (Exception e) {
                System.out.println("RESPONSE ERROR" + e.getMessage());
            }
        }
        return res;

    }

    public List get(GetCustomer format) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(format);
        SendToServer serverSend = new SendToServer(json, this.socket);
        List<String> res = new ArrayList<>();
        if (serverSend.send()) {
            try {
                InputStream input = this.socket.getInputStream();
                ObjectInputStream objectInput = new ObjectInputStream(input);
                res = (List) objectInput.readObject();
                JsonNode jsonNode = objectMapper.readTree(res.get(0));

                if (jsonNode.get("status").asInt() == 404) {
                    System.out.println("\n\t\t\t*******************************************************************************************************");
                    System.out.println("                                                  NO CUSTOMER FOUND                                            ");
                    System.out.println("\t\t\t*******************************************************************************************************");
                    return null;
                }

            } catch (Exception e) {
                System.out.println("RESPONSE ERROR HERE" + e.getMessage());
            }
        }
        return res;
    }
}