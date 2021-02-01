package com.customify.server.controllers;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ProductController {
    DataOutputStream output;

    public void registerProduct(Socket socket)throws IOException {
        output = new DataOutputStream(socket.getOutputStream());
        output.writeUTF("ProductController was registered successfully");
    }

    public void updateProduct(Socket socket) throws IOException{
        output = new DataOutputStream(socket.getOutputStream());
        output.writeUTF("ProductController was updated successfully");
    }

    public void deleteProduct(Socket socket)throws IOException {
        output = new DataOutputStream(socket.getOutputStream());
        output.writeUTF("ProductController was deleted successfully");
    }

    public void getAllProducts(Socket socket) throws IOException {
        output = new DataOutputStream(socket.getOutputStream());
        output.writeUTF("Products list here...");
    }


    public void getProduct(Socket socket) throws IOException{
        output = new DataOutputStream(socket.getOutputStream());
        output.writeUTF("Single product");
    }
}



