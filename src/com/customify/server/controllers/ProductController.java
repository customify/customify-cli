package com.customify.server.controllers;

import com.customify.server.Db.Db;
import com.customify.server.models.ProductModel;
import com.customify.shared.Request;
import com.customify.shared.Response;
import com.customify.shared.requests_data_formats.ProductFormat;

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
        ProductFormat product = (ProductFormat) request.getObject();

        System.out.println("Request to create product was received at backend");

        try {
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
            preparedStatement.setString(9,product.getCreatedAt());

            OutputStream output = this.socket.getOutputStream();
            ObjectOutputStream objectOutput =  new ObjectOutputStream(output);

            if(preparedStatement.executeUpdate() > 0){
                List responseData = new ArrayList<>();
                Response response = new Response(200,product);
                responseData.add(response);

                //Sending the response to client
                objectOutput.writeObject(responseData);
            }
            else{
                List responseData = new ArrayList<>();
                Response response = new Response(400,product);
                responseData.add(response);
                //Sending the response to client
                objectOutput.writeObject(responseData);
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



