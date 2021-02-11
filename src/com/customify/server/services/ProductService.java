/**
 * @description A service to provide all required methods for Manipulating Products in DB.
 * @author TWIZEYIMANA Jacques,SAUVE Jean-Luc
 * @version 2
 */
package com.customify.server.services;

import com.customify.server.response_data_format.product.GetAllProductsFormat;
import com.customify.server.Db.Db;
import com.customify.shared.Response;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.customify.server.response_data_format.product.ProductFormat;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private Socket socket;
    OutputStream output;
    ObjectOutputStream objectOutput;


    public ProductService(Socket socket) throws IOException {
        this.socket = socket;
        this.output = socket.getOutputStream();
        this.objectOutput = new ObjectOutputStream(output);
    }

    /*
    * @author: Jacques TWIZEYIMANA
    * @description: register a new product in database
    * */
    public void registerProduct(String data) throws IOException, SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(data);

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
                this.objectOutput.writeObject("{\"status\": 201}");
                this.objectOutput.close();
                System.out.println("Product was Created successfully!!! ");
            } else {
                this.objectOutput.writeObject("{\"status\": 400}");
                this.objectOutput.close();
                System.out.println("\n\nProduct format received was incorrect\n ");
            }
        } catch (Exception e) {
            this.objectOutput.writeObject("{\"status\": 500}");
            this.objectOutput.close();
            System.out.println("\n\nInternal server error\n ");
            System.out.println(e.getMessage() + e.getCause());
        }
    }

    /*
    @author:: Jacques TWIZEYIMANA
    description: get all registered products
     */

    public void getAllProducts() throws IOException, SQLException {
        System.out.println("request to get all products was received");
        Statement statement = Db.getStatement();
        try {
            ResultSet records = statement.executeQuery("SELECT * FROM products");
            List<ProductFormat> products = new ArrayList<>();

            while (records.next()) {
                products.add(new ProductFormat(records.getInt("id"), records.getLong("product_code"), records.getInt("business_id"), records.getString("name"), records.getFloat("price"), records.getInt("quantity"), records.getString("description"), records.getDouble("bonded_points"), records.getInt("registered_by"), records.getString("created_at")));
            }

            GetAllProductsFormat format = new GetAllProductsFormat(200, products);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(format);

            //Sending the response to server after it has been formatted
            this.objectOutput.writeObject(json);
        } catch (Exception e) {
            e.printStackTrace();
            this.objectOutput.writeObject(new GetAllProductsFormat(500, new ArrayList<ProductFormat>()));
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
                System.out.println("Reached Here 5");
                System.out.println("Sent ID: " + jsonNode.get("id").asText());

                productFormat.setProductCode(rs.getLong("product_code"));
                productFormat.setBusinessId(rs.getInt("business_id"));
                productFormat.setName(rs.getString("name"));
                productFormat.setPrice(rs.getFloat("price"));
                productFormat.setQuantity(rs.getInt("quantity"));
                productFormat.setDescription(rs.getString("description"));
                productFormat.setBondedPoints(rs.getDouble("bonded_points"));
                productFormat.setRegisteredBy(rs.getInt("registered_by"));
                productFormat.setCreatedAt(rs.getString("created_at"));
                System.out.println("Reached here 6");
            }

            System.out.println("Name: " + productFormat.getName());

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

    }


    /**
     * @description
     * Function to Update a Product
     * @author SAUVE Jean-Luc
     * @version 1
     * */


    public void updateProduct(String data) throws IOException, SQLException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(data);

        OutputStream output = this.socket.getOutputStream();
        ObjectOutputStream objectOutput = new ObjectOutputStream(output);

        Statement stmt = null;
        Connection conn = null;

        try {
            conn = Db.getConnection();

            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "UPDATE products SET product_code = " + jsonNode.get("productCode").asText() + ",business_id = " + jsonNode.get("business_id").asText() +
                    ",name=" + jsonNode.get("name").asText() + ",price=" + jsonNode.get("price").asText() + ",quantity=" + jsonNode.get("quantity").asText() +
                    ",description = " + jsonNode.get("description").asText() + ",bonded_points=" + jsonNode.get("bondedPoints").asText() +
                    ",registered_by = " + jsonNode.get("registered_by").asText() + ",created_at = '2021-02-04' WHERE id = " + jsonNode.get("id").asText();

            stmt.executeUpdate(sql);

//            List responseData = new ArrayList<>();
//            Response response = new Response(200,product);
//            responseData.add(response);

            //Sending the response to client
//            objectOutput.writeObject(responseData);

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            List responseData = new ArrayList<>();
            Response response = new Response(500, new ProductFormat());
            responseData.add(response);
            //Sending the response to client
            objectOutput.writeObject(responseData);
        }
    }

    // Tamara update this according to new Structure

//    public void deleteProduct() throws IOException {
//        Long product = (Long) request.getObject();
//        OutputStream output = this.socket.getOutputStream();
//        ObjectOutputStream objectOutput =  new ObjectOutputStream(output);
//
//        try {
//
//            Connection connection = Db.getConnection();
//            String sql = "DELETE from products where product_code= ?;";
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setLong(1,product);
//
//
//            if(preparedStatement.executeUpdate() > 0){
//                List responseData = new ArrayList<>();
//                Response response = new Response(200,product);
//                responseData.add(response);
//
//                //Sending the response to client
//                objectOutput.writeObject(responseData);
//            }
//            else{
//                List responseData = new ArrayList<>();
//                Response response = new Response(400,product);
//                responseData.add(response);
//                //Sending the response to client
//                objectOutput.writeObject(responseData);
//            }
//        }
//        catch (Exception e){
//            List responseData = new ArrayList<>();
//            Response response = new Response(500,new ProductFormat());
//            responseData.add(response);
//            //Sending the response to client
//            objectOutput.writeObject(responseData);
//        }
//    }

}