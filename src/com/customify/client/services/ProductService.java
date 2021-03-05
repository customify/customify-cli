package com.customify.client.services;

import com.customify.client.Colors;
import com.customify.client.Keys;
import com.customify.client.SendToServer;

import com.customify.client.data_format.products.ProductFormat;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private Socket socket;
    private String data;

    InputStream inputStream;
    ObjectInputStream objectInputStream;

    public ProductService(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) throws IOException {
        this.socket = socket;

    }

    public void addNewProduct(ProductFormat product) throws Exception {
        product.setKey(Keys.CREATE_PRODUCT);
        String request = new ObjectMapper().writeValueAsString(product);

        SendToServer sendToServer = new SendToServer(request,this.socket);
        if (sendToServer.send()){
            this.handleRegisterProductSuccess();
        }
        else System.out.println("\n\n\t\tSENDING REQUEST TO SERVER FAILED\n");
    }


    /**
     * @description
     * Service to Retrieve Product By Id
     * @author SAUVE Jean-Luc
     * @version 1
     * */
    public void getProductById(Integer productId) throws  Exception{

        String jsonToSend = "{\"key\" : \"GET_PRODUCT_BY_ID\", \"id\" : \""+productId+"\"}";
        SendToServer sendToServer = new SendToServer(jsonToSend, this.socket);

        //if the sending is successful call a method to handle response from server
        if (sendToServer.send()) {
            this.handleGetProductByIdSuccess();
        }
        else{
            System.out.println("\n\nError occurred when trying to send request to server\n");
        }

    }
    /**
     * @description
     * Function to Delete a Product
     * @author Merlyne Iradukunda
     * Due date: 6/2/2020
     * @version 1
     * */
    public void deleteProduct(ProductFormat product) throws  Exception{
        ObjectMapper mapper = new ObjectMapper();
        String jsonProductFormat = mapper.writeValueAsString(product);
        SendToServer serverSend = new SendToServer(jsonProductFormat, this.socket);
        if (serverSend.send()) {
            this.handleDeleteProductSuccess();
        } else {
            System.out.println(Colors.ANSI_RED+"FAILED TO SEND DATA TO SERVER "+Colors.ANSI_RED);
        }
    }

    public void getAllProducts() throws Exception {
        ProductFormat format = new ProductFormat();
        format.setKey(Keys.GET_ALL_PRODUCTS);

        SendToServer sendToServer = new SendToServer(new ObjectMapper().writeValueAsString(format),this.socket);
        if (sendToServer.send()) {
            this.handleGetProductListSuccess();
        }
        else System.out.println("\n\n\t\t\tERROR OCCURRED WHEN SENDING REQUEST TO SERVER\n");
    }

    /**
     * @description
     * Service to Update Product By Id
     * @author SAUVE Jean-Luc
     * @version 1
     * */

    public void updateProduct(ProductFormat productFormat) throws  Exception{

        productFormat.setKey(Keys.UPDATE_PRODUCT);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(productFormat);
        SendToServer serverSend = new SendToServer(json, this.socket);
        //if the sending is successful call a method to handle response from server
        if (serverSend.send()) {
            this.handleUpdateProductSuccess();
        }
        else{
            System.out.println("\n\nError occurred when trying to send request to server\n");
        }
    }
    public void handleGetProductListSuccess() throws IOException, ClassNotFoundException {
        try {
            InputStream input =this.socket.getInputStream();
            ObjectInputStream objectInput = new ObjectInputStream(input);

            List<String> res = (List) objectInput.readObject();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode response = objectMapper.readTree(res.get(0));

            if(response.get("status").asInt() == 500){
                System.out.println("\n\n\t\t\t---- INTERNAL SERVER ERROR -----\n");
                return;
            }

            final JsonNode products = response.get("products");
            if (products.isArray()) {
                System.out.println("\n\t\t\t----------------------------------------------------------------------------------------------------------------------------");
                System.out.println("\t\t\t\t\t\t\t\t\t\tHere is a list of products registered so far");
                System.out.println("\t\t\t----------------------------------------------------------------------------------------------------------------------------\n");
                System.out.println(String.format("\t\t\t\t%-15s %-30s %-10s %10s %20s %20s", "Code", "name", "quantity", "price", "bounded points", "Created at") + "\n");
                System.out.println("\t\t\t----------------------------------------------------------------------------------------------------------------------------\n");
                for (final JsonNode product : products) {
                    System.out.println(String.format("\t\t\t\t%-15s %-30s %-10s %10s %20s %20s", product.get("productCode").asText(), product.get("name").asText(), product.get("quantity").asText(), product.get("price").asText(), product.get("bondedPoints").asText(), product.get("createdAt").asText()));
                }
                System.out.println("\t\t\t----------------------------------------------------------------------------------------------------------------------------\n");
            }
        }catch(Exception e){
            System.out.println("RESPONSE ERROR" + e.getMessage());
        }

        return;
    }

    public void handleRegisterProductSuccess() throws IOException, ClassNotFoundException {
        try {
            InputStream input =this.socket.getInputStream();
            ObjectInputStream objectInput = new ObjectInputStream(input);

            List<String> res = (List) objectInput.readObject();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode response = objectMapper.readTree(res.get(0));

            if (response.get("status").asInt() == 201) System.out.println(Colors.ANSI_GREEN+"\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPRODUCT CREATED SUCCESSFULLY\n\n"+Colors.ANSI_RESET);
            else if(response.get("status").asInt() == 400) System.out.println(Colors.ANSI_RED+"\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tBAD FORMAT WAS SUPPLIED TO SOME FIELDS\n\n"+Colors.ANSI_RESET);
            else if(response.get("status").asInt() == 500) System.out.println(Colors.ANSI_RED+"\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tBACKEND INTERNAL SERVER ERROR.\n\n"+Colors.ANSI_RESET);
            else System.out.println("\n\n\t\t\tUNKNOWN ERROR OCCURRED WHEN SENDING AND RECEIVING RESPONSE\n\n");
        }catch(Exception e){
            System.out.println(Colors.ANSI_RED+"\n\n\t\t\t\t\t\t\tERROR OCCURRED.TRY AGAIN\n\n"+Colors.ANSI_RESET);
        }
        return;
    }

    /**
     * @description
     * Function to Send Response when Product is Retrieved Successfully
     * @author SAUVE Jean-Luc
     * @version 1
     * */

    public void handleGetProductByIdSuccess() throws IOException, ClassNotFoundException{
        try {
            InputStream input = this.socket.getInputStream();
            ObjectInputStream objectInput = new ObjectInputStream(input);

            List<String> response = (List) objectInput.readObject();
            String json_response = response.get(0);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json_response);

            if(jsonNode.get("status").asInt() == 200){
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("Product Code: " + jsonNode.get("productCode").asText());
                System.out.println("Business Id: "+ jsonNode.get("business_id").asText());
                System.out.println("Name: " + jsonNode.get("name").asText());
                System.out.println("Price: " + jsonNode.get("price").asText() );
                System.out.println("Quantity: " + jsonNode.get("quantity").asText());
                System.out.println("Description: " + jsonNode.get("description").asText());
                System.out.println("Bonded Points: " + jsonNode.get("bondedPoints").asText());
                System.out.println("Registered By: " + jsonNode.get("registered_by").asText());
                System.out.println("Created At: " + jsonNode.get("createdAt").asText());
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
            }
            else if(jsonNode.get("status").asInt() == 400){
                System.out.println("\n\nInvalid product format.Please enter product details as required\n\n");
            }
            else{
                System.out.println("\n\nUnknown error occurred.Check your internet connection\n");
            }

        } catch (IOException e) {
            System.out.println("\n\nError occurred:" +e.getMessage()+ "\n\n");
        } catch (ClassNotFoundException e) {
            System.out.println("\n\nError occurred:" +e.getMessage()+ "\n\n");
        }

        return;
    }
    public void handleDeleteProductSuccess() throws  Exception, ClassNotFoundException {
        try {
            inputStream = this.socket.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);
            ObjectMapper objectMapper=new ObjectMapper();

            String data = (String) objectInputStream.readObject();
            JsonNode jsonFormat = objectMapper.readTree(data);
            int statusCode = jsonFormat.get("StatusCode").asInt();

            if (statusCode == 200) {
                System.out.println(Colors.ANSI_GREEN+"\n\t\t\t\t\t\t\t\t\t\t\t\t\t PRODUCT DELETED SUCCESSFULLY "+Colors.ANSI_GREEN);
            }
            else{
                System.out.println(Colors.ANSI_RED+"\n\t\t\t\t\t\t\t\t\t\t\t\t\t INVALID PRODUCT CODE \n"+Colors.ANSI_RED);
            }
        } catch (Exception e) {
            System.out.println(Colors.ANSI_RED+"\n\t\t\t\t\t\t\t\t\t\t\t\t\t ERROR OCCURED :" + e.getMessage() + "\n\n"+Colors.ANSI_RED);
        }
    }

    /**
     * @description
     * Function to Send Response when Product is Updated Successfully
     * @author SAUVE Jean-Luc
     * @version 1
     * */

    public void handleUpdateProductSuccess() throws IOException, ClassNotFoundException {
        try {
            InputStream input = this.socket.getInputStream();
            ObjectInputStream objectInput = new ObjectInputStream(input);
//            String json_response = (String) objectInput.readObject();
            List<String> response = (List) objectInput.readObject();
            String json_response = response.get(0);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json_response);

            if(jsonNode.get("status").asInt() == 200){
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("|                                         |");
                System.out.println("|       Product Updated Successfully      |");
                System.out.println("|                                         |");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
            }
            else if(jsonNode.get("status").asInt() == 400){
                System.out.println("\n\nInvalid product format.Please enter product details as required\n\n");
            }
            else{
                System.out.println("\n\nUnknown error occurred.Check your internet connection\n");
            }

        } catch (IOException e) {
            System.out.println("\n\nError occurred:" +e.getMessage()+ "\n\n");
        } catch (ClassNotFoundException e) {
            System.out.println("\n\nError occurred:" +e.getMessage()+ "\n\n");
        }

        return;
    }

}