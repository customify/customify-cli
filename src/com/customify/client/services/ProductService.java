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
        inputStream = this.socket.getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);

    }

    public void addNewProduct(ProductFormat productFormat) throws Exception {
        Request request = new Request(Keys.CREATE_PRODUCT, productFormat);
        Common common = new Common(request, this.socket);

        //if the sending is successful call a method to handle response from server
        if (common.sendToServer() == true) {
            this.handleRegisterProductSuccess();
        }
    }

    public void handleRegisterProductSuccess() throws IOException, ClassNotFoundException {
        List<Response> response = (List<Response>) objectInputStream.readObject();
        if(response.get(0).getStatusCode() == 200){
            ProductFormat registeredProduct = (ProductFormat) response.get(0).getData();

            System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("\t\t product registered successfully");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        }
    }

}
