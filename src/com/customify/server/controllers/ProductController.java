package com.customify.server.controllers;

/*
Created by Jacques Twizeyimana
This a product controller class to handle all logics regarding product
It has createProduct,getAll,UpdateProduct,getProductByI and deleteProduct methods
*/

import com.customify.server.Db.Db;
import com.customify.server.models.ProductModel;
import com.customify.shared.Request;
import com.customify.shared.Response;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductController {

    DataOutputStream output;
    private Socket socket;
    private Request request;

    public ProductController(Socket socket, Request request) throws IOException {
        this.socket = socket;
        this.request = request;
    }

    public void registerProduct() throws IOException, SQLException {
        ProductModel product = (ProductModel) request.getObject();

        Connection connection = Db.getConnection();
        String sql = "INSERT INTO products(product_code,business_id,name,price,quantity,description,bonded_points,registered_by,created_at)" +
                " values(?,?,?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1,product.getProductCode());
        preparedStatement.setInt(2,product.getBusiness_id());
        preparedStatement.setString(3,product.getName());
        preparedStatement.setFloat(4,product.getPrice());
        preparedStatement.setInt(5,product.getQuantity());
        preparedStatement.setString(6,product.getDescription());
        preparedStatement.setDouble(7,product.getBondedPoints());
        preparedStatement.setInt(8,product.getRegistered_by());
        preparedStatement.setDate(9, (Date) product.getCreatedAt());

        try {
            OutputStream output = this.socket.getOutputStream();
            ObjectOutputStream objectOutput =  new ObjectOutputStream(output);

            if(preparedStatement.execute()){
                Response response = new Response(200,product);
                List responseData = new ArrayList<>();
                responseData.add(response);

                //Send the response to client
                objectOutput.writeObject(responseData);
            }
            else{
                System.out.println("Error occurred when registering product");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void updateProduct() throws IOException {
        output = new DataOutputStream(this.socket.getOutputStream());
        output.writeUTF("ProductController was updated successfully");
    }

    public void deleteProduct() throws IOException {
        output = new DataOutputStream(this.socket.getOutputStream());
        output.writeUTF("Product was deleted successfully");
    }

    public void getAllProducts() throws IOException {
        output = new DataOutputStream(this.socket.getOutputStream());
        output.writeUTF("Products list here...");
    }


    public void getProduct() throws IOException {
        output = new DataOutputStream(this.socket.getOutputStream());
        output.writeUTF("Single product with ID: ");
    }
}



