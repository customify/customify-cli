/**
 * @description A service to provide all required methods for Manipulating Products in DB.
 * @author TWIZEYIMANA Jacques
 * @version 2
 */
package com.customify.server.services;

import com.customify.server.CustomizedObjectOutputStream;
import com.customify.server.Db.Db;
import com.customify.server.SendToClient;
import com.customify.server.response_data_format.products.GetAllProductsFormat;
import com.customify.server.response_data_format.products.ProductFormat;
import com.customify.server.utils.MailCustomers;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    List<String> responseData = new ArrayList<>();
    ObjectOutputStream objectOutput;
    Socket socket;
    String json_data;
    OutputStream output;

    public ProductService(Socket socket) throws IOException {
        this.output = socket.getOutputStream();
        this.objectOutput = new CustomizedObjectOutputStream(this.output);
        this.socket = socket;
    }

    public static void deleteProduct() {
    }
    /*
     * @author: Jacques TWIZEYIMANA
     * description: Register a new product in database*
     */

    public void registerProduct(String data) throws IOException, SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(data);

        String response = "";

        try {

            Connection connection = Db.getConnection();
            String sql = "INSERT INTO products(product_code,business_id,name,price,quantity,description,bonded_points,registered_by,created_at)" +
                    " values(?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, jsonNode.get("productCode").asLong());
            preparedStatement.setInt(2, jsonNode.get("business_id").asInt());
            preparedStatement.setString(3, jsonNode.get("name").asText());
            preparedStatement.setFloat(4, (float) jsonNode.get("price").asDouble());
            preparedStatement.setInt(5, jsonNode.get("quantity").asInt());
            preparedStatement.setString(6, jsonNode.get("description").asText());
            preparedStatement.setDouble(7, jsonNode.get("bondedPoints").asDouble());
            preparedStatement.setInt(8, jsonNode.get("registered_by").asInt());
            preparedStatement.setString(9, jsonNode.get("createdAt").asText());

            if (preparedStatement.executeUpdate() > 0) {
                response = "{\"status\": \"201\"}";
                System.out.println("Product was Created successfully!!! ");
                new MailCustomers(jsonNode.get("name").asText()).start();
            } else {
                response = "{\"status\": \"400\"}";
                System.out.println("\n\nProduct format received was incorrect\n ");
            }
        } catch (Exception e) {
            response = "{\"status\": \"500\"}";
            System.out.println("\n\nInternal server error:" + e.getMessage() + e.getCause());
        } finally {
            responseData.add(response);
            objectOutput.writeObject(responseData);
        }
    }

    /**
     * @description
     * Function to Get Product by ID from DB and send Response to Client
     * @author SAUVE Jean-Luc
     * @version 1
     * */

    public void getProductById(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(data);


        Statement stmt = null;
        Connection conn = null;
        try {

            ProductFormat productFormat = new ProductFormat();

            //Open a connection
            conn = Db.getConnection();

            //Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT product_code,business_id,name,price,quantity,description,bonded_points,registered_by,created_at FROM products WHERE id= " + jsonNode.get("id").asText() + " ;";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                System.out.println("Sent ID: " + jsonNode.get("id").asText());

                productFormat.setProductCode(rs.getLong("product_code"));
                productFormat.setBusiness_id(rs.getInt("business_id"));
                productFormat.setName(rs.getString("name"));
                productFormat.setPrice(rs.getFloat("price"));
                productFormat.setQuantity(rs.getInt("quantity"));
                productFormat.setDescription(rs.getString("description"));
                productFormat.setBondedPoints(rs.getDouble("bonded_points"));
                productFormat.setRegistered_by(rs.getInt("registered_by"));
                productFormat.setCreatedAt(rs.getString("created_at"));
            }
            productFormat.setStatus(200);
            System.out.println("Name: " + productFormat.getName());

            responseData.clear();
            responseData.add(new ObjectMapper().writeValueAsString(productFormat));
            objectOutput.writeObject(responseData);

//            rs.close();
//            stmt.close();
//            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }


    }


    /**
     * @description
     * Function to Update a Product
     * @author SAUVE Jean-Luc
     * @version 1
     * */


    public void updateProduct(String data) throws IOException, SQLException {
        System.out.println("Called updateProducts");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(data);


        Statement stmt = null;
        Connection conn = null;

        try {
            conn = Db.getConnection();

            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "UPDATE products SET product_code = " + jsonNode.get("productCode").asText() + ",business_id = " + jsonNode.get("business_id").asText() +
                    ",name = '" + jsonNode.get("name").asText() + "',price=" + jsonNode.get("price").asText() + ",quantity=" + jsonNode.get("quantity").asText() +
                    ",description = '" + jsonNode.get("description").asText() + "',bonded_points = " + jsonNode.get("bondedPoints").asText() +
                    ",registered_by = " + jsonNode.get("registered_by").asText() + ",created_at = '" + jsonNode.get("createdAt").asText() + "' WHERE id = " + jsonNode.get("id").asText();

            stmt.executeUpdate(sql);

            ProductFormat productFormat = new ProductFormat();
            productFormat.setStatus(200);

//            String response = "{\"status\": \"200\"}";


            responseData.add(new ObjectMapper().writeValueAsString(productFormat));
            objectOutput.writeObject(responseData);


//            stmt.close();
//            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            List responseData = new ArrayList<>();
//            Response response = new Response(500,new ProductFormat());
            responseData.add("{\"status\":\"500\"}");
            //Sending the response to client
            objectOutput.writeObject(responseData);
        }
    }


    public void deleteProduct(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(data);
        String jsonSendStatusCode = null;
        int statusCode;
        try {
            Connection connection = Db.getConnection();
            String sql = "DELETE from products where product_code= ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, jsonNode.get("productCode").asLong());
            statusCode = (preparedStatement.executeUpdate() > 0) ? 200 : 400;
            jsonSendStatusCode = "{ \"StatusCode\" : \"" + statusCode + "\"}";
            // System.out.println("Status code [Server side]:"+jsonSendStatusCode);
            objectOutput.writeObject(jsonSendStatusCode);

        }catch (Exception e){
            System.out.println("Exception Message ==> "+e.getMessage());
        }
    }

    // Jacques update this according to new Structure

    public void getAllProducts() throws IOException, SQLException {
        System.out.println("Request to get all products");
        List products = new ArrayList<ProductFormat>();
        GetAllProductsFormat allProductsFormat = new GetAllProductsFormat();

        try {
            Statement statement = Db.getStatement();
            ResultSet records = statement.executeQuery("SELECT * FROM products");

            while (records.next()) {
                ProductFormat product = new ProductFormat(
                        records.getInt("business_id"), records.getString("name"),
                        records.getFloat("price"), records.getInt("quantity"),
                        records.getString("description"), records.getDouble("bonded_points"),
                        records.getInt("registered_by"), records.getString("created_at")
                );

                product.setProductCode(records.getLong("product_code"));
                products.add(product);
            }
            allProductsFormat.setProducts(products);
            allProductsFormat.setStatus(200);
        } catch (SQLException e) {
            allProductsFormat.setStatus(500);
            System.out.println(e.getMessage());
        } finally {
            responseData.clear();
            responseData.add(new ObjectMapper().writeValueAsString(allProductsFormat));
            objectOutput.writeObject(responseData);
        }
    }

//    public void getProduct() throws IOException {
//        output = new DataOutputStream(this.socket.getOutputStream());
//        output.writeUTF("Single product with ID: ");
//    }
}
