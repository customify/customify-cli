package com.customify.client.services;

/*
* Created by Jacques Twizeyimana
* This class will be used to send requests from client to server and return response from server to client
* all API calls for product module will be handled here
*/

import com.customify.client.Common;
import com.customify.server.models.ProductModel;
import com.customify.shared.Keys;
import com.customify.shared.Request;
import com.customify.shared.Response;

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
        inputStream = this.socket.getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);

    }

    public void addNewProduct(ProductModel productModel) throws Exception {
        Request request = new Request(Keys.CREATE_PRODUCT, productModel);
        Common common = new Common(request, this.socket);

        //if the sending is successful call a method to handle response from server
        if (common.sendToServer()) {
            this.handleRegisterProductSuccess();
        }
    }

    public void handleRegisterProductSuccess() throws IOException, ClassNotFoundException {
        inputStream = this.socket.getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);
        List<Response> response = (List<Response>) objectInputStream.readObject();

        if(response.get(0).getStatusCode() == 200){
            ProductModel registeredProduct = (ProductModel) response.get(0).getData();

            System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("\t\t product registered successfully");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        }
        else{
            System.out.println("error occurred when receiving response data");
        }
    }

}
