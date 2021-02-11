package com.customify.client.services;

import com.customify.client.Common;
import com.customify.client.SendToServer;
import com.customify.client.Keys;
import com.customify.shared.Request;
import com.customify.shared.Response;
import com.customify.client.data_format.products.ProductFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static com.customify.client.Keys.CREATE_PRODUCT;
import static com.customify.client.Keys.GET_ALL_PRODUCTS;

public class ProductService {
    private Socket socket;
    private InputStream inputStream;
    private ObjectInputStream objectInputStream;

    public ProductService(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) throws IOException {
        this.socket = socket;

    }

    /*@author: JACQUES TWIZEYIMANA
    * Description: a register a new product in database
    * */

    public void addNewProduct(ProductFormat productFormat) throws Exception {
        productFormat.setKey(CREATE_PRODUCT);

        var mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(productFormat);
        SendToServer sendToServer = new SendToServer(json, this.socket);

        if (sendToServer.send()) {
            System.out.println("\n\nData was sent to server successfully..\n");
            this.handleRegisterProductSuccess();
        }
        else System.out.println("\n\nFailed to send the request to the server ....\n");
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
            System.out.println("Reached here 1");
            this.handleGetProductByIdSuccess();
        }
        else{
            System.out.println("\n\nError occurred when trying to send request to server\n");
        }

    }
    //Method Created By Merlyne Iradukunda
    // Due date: 6/2/2020
  /*  public void deleteProduct(Long productCode) throws  Exception{
        Request request = new Request(Keys.DELETE_PRODUCT, productCode);
        Common common = new Common(request, this.socket);

        //if the sending is successful call a method to handle response from server
        if (common.sendToServer()) {
            this.handleDeleteProductSuccess();
        } else {
            System.out.println("\n\nError occurred when trying to send request to server\n");
        }
    }*/

    /*@author: Jacques TWIZEYIMANA
    * Description: get all products from database
    * */
    public void getAllProducts() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ProductFormat format = new ProductFormat();
        format.setKey(GET_ALL_PRODUCTS);

        System.out.println("get all products from service");

        String json = objectMapper.writeValueAsString(format);

        SendToServer serverSend = new SendToServer(json, this.socket);
        if(serverSend.send()){
            System.out.println("\n\nRequest to get all products was sent\n");
            this.handleGetProductListSuccess();
        }else {
            System.out.println("\n\nError occurred when trying to send request to server\n");
        }
    }

    /**
     * @description
     * Service to Update Product By Id
     * @author SAUVE Jean-Luc
     * @version 1
     * */

    public void updateProduct(ProductFormat productFormat) throws  Exception{

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

    /*@author: Jacques TWIZEYIMANA
    * description: get handle get all products response
    * */
    public void handleGetProductListSuccess() throws IOException, ClassNotFoundException {
        try {
            this.inputStream = this.socket.getInputStream();
            this.objectInputStream = new ObjectInputStream(this.inputStream);
            String json_data = (String)this.objectInputStream.readObject();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json_data);

            if(jsonNode.get("status").asInt() == 200){

                List<ProductFormat> products;
                products = objectMapper.treeToValue(jsonNode.get("products"),new ArrayList<ProductFormat>().getClass()) ;

                if (products.size() == 0) {
                    System.out.println("\n\nNo products registered so far.\n");
                    return;
                }

                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("\t\t\t\t\t\t\tHere is a list of products registered so far");
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");

                System.out.println(String.format("%-15s %-30s %-10s %10s %20s %20s", "Code", "name", "quantity", "price", "bounded points", "Created at") + "\n");

                for (int i = 0; i < products.size(); i++) {
                    ProductFormat product = products.get(i);

                    System.out.println(String.format("%-15s %-30s %-10s %10s %20s %20s", product.getProductCode(), product.getName(), product.getQuantity(), product.getPrice(), product.getBondedPoints(), product.getCreatedAt()));
                }
                System.out.println("\n");
            } else if (jsonNode.get("status").asInt() == 400) {
                System.out.println("\n\nInvalid product format.Please enter product details as required\n\n");
            } else {
                System.out.println("\n\nUnknown error occurred.Check your internet connection\n");
            }

        } catch (IOException e) {
            System.out.println("\n\nError occurred:" + e.getMessage() + "\n\n");
        } catch (ClassNotFoundException e) {
            System.out.println("\n\nError occurred:" + e.getMessage() + "\n\n");
        }

        return;
    }

/*@author: Jacques TWIZEYIMANA
* Description: handle register product response data
* */

    public void handleRegisterProductSuccess() throws Exception {
        try {
            this.inputStream = this.socket.getInputStream();
            this.objectInputStream = new ObjectInputStream(this.inputStream);

            String json_data = (String) this.objectInputStream.readObject();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json_data);

            if (jsonNode.get("status").asInt() == 201) {
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("\t\t product registered successfully");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
            } else if (jsonNode.get("status").asInt() == 400) {
                System.out.println("\n\nInvalid product format.Please enter product details as required\n\n");
            } else {
                System.out.println("\n\nUnknown error occurred.Check your internet connection\n");
            }

        } catch (IOException e) {
            System.out.println("\n\nError occurred io:" + e.getMessage() + "\n\n");
        } catch (ClassNotFoundException e) {
            System.out.println("\n\nError occurred c4:" + e.getMessage() + "\n\n");
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
        System.out.println("Reached here 2");
        inputStream = this.getSocket().getInputStream();
        System.out.println("Reached here 3");
        objectInputStream = new ObjectInputStream(inputStream);
        System.out.println("Reached here 4");
        try {
            List<Response> response = (List<Response>) objectInputStream.readObject();
            if(response.get(0).getStatusCode() == 200){
                ProductFormat retrievedProduct = (ProductFormat) response.get(0).getData();

                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("Product Code: " + retrievedProduct.getProductCode());
                System.out.println("Business Id: "+ retrievedProduct.getBusiness_id());
                System.out.println("Name: " + retrievedProduct.getName());
                System.out.println("Price: " + retrievedProduct.getPrice() );
                System.out.println("Quantity: " + retrievedProduct.getQuantity());
                System.out.println("Description: " + retrievedProduct.getDescription());
                System.out.println("Bonded Points: " + retrievedProduct.getBondedPoints());
                System.out.println("Registered By: " + retrievedProduct.getRegistered_by());
                System.out.println("Created At: " + retrievedProduct.getCreatedAt());
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
            }
            else if(response.get(0).getStatusCode() == 400){
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
        inputStream = this.getSocket().getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);
        try {
            List<Response> response = (List<Response>) objectInputStream.readObject();

            if (response.get(0).getStatusCode() == 200) {
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("\t\t product deleted successfully");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
            } else if (response.get(0).getStatusCode() == 400) {
                System.out.println("\n\nInvalid product format.Please enter product details as required\n\n");
            } else if(response.get(0).getStatusCode() == 500){
                System.out.println("Internal server error!!");
            }else{
                System.out.println("\n\nUnknown error occurred.Check your internet connection\n");
            }
        } catch (IOException e) {
            System.out.println("\n\nError occurred:" + e.getMessage() + "\n\n");
        } catch (ClassNotFoundException e) {
            System.out.println("\n\nError occurred:" + e.getMessage() + "\n\n");
        }

        return;
    }

    /**
     * @description
     * Function to Send Response when Product is Updated Successfully
     * @author SAUVE Jean-Luc
     * @version 1
     * */

    public void handleUpdateProductSuccess() throws IOException, ClassNotFoundException {
        inputStream = this.getSocket().getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);
        try {
            List<Response> response = (List<Response>) objectInputStream.readObject();
            System.out.println("Status: "+ response.get(0).getStatusCode());
            if(response.get(0).getStatusCode() == 200){
                ProductFormat registeredProduct = (ProductFormat) response.get(0).getData();

                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("\t\t product Updated successfully");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
            }
            else if(response.get(0).getStatusCode() == 400){
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