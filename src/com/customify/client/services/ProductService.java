package com.customify.client.services;

import com.customify.client.Common;
import com.customify.server.models.ProductModel;
import com.customify.shared.Keys;
import com.customify.shared.Request;
import com.customify.shared.Response;
import com.customify.shared.requests_data_formats.ProductFormat;
import com.customify.shared.responses_data_format.AuthFromats.SuccessLoginFormat;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
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

    public void addNewProduct(ProductFormat productModel) throws Exception {
        Request request = new Request(Keys.CREATE_PRODUCT, productModel);
        Common common = new Common(request, this.socket);

        //if the sending is successful call a method to handle response from server
        if (common.sendToServer() == true) {
            this.handleRegisterProductSuccess();
        }
        else{
            System.out.println("\n\nError occurred when trying to send request to server\n");
        }
    }

    /**
     * @description
     * Service to Retrieve Product By Id
     * @author SAUVE Jean-Luc
     * @version 1
     * */
    public void getProductById(Integer productId) throws  Exception{
        Request request = new Request(Keys.GET_PRODUT_BY_ID,productId);
        Common common = new Common(request, this.socket);

        //if the sending is successful call a method to handle response from server
        if (common.sendToServer() == true) {
            this.handleGetProductByIdSuccess();
        }
        else{
            System.out.println("\n\nError occurred when trying to send request to server\n");
        }

    }

    public void handleRegisterProductSuccess() throws IOException, ClassNotFoundException {
        inputStream = this.getSocket().getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);

        try {
            List<Response> response = (List<Response>) objectInputStream.readObject();
            if(response.get(0).getStatusCode() == 200){
                ProductFormat registeredProduct = (ProductFormat) response.get(0).getData();

                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("\t\t product registered successfully");
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

    public void handleGetProductByIdSuccess() throws IOException, ClassNotFoundException{
        inputStream = this.getSocket().getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);

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

}