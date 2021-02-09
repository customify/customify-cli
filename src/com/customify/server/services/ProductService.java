/**
 * @description
 * A service to provide all required methods for Manipulating Products in DB.
 * @author SAUVE Jean-Luc
 * @version 1
 * */
package com.customify.server.services;

import com.customify.server.Db.Db;
import com.customify.server.models.ProductModel;
import com.customify.shared.Request;
import com.customify.shared.Response;
import com.customify.shared.requests_data_formats.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private Socket socket;


    public ProductService(Socket socket){
        this.socket = socket;
    }

    // Jacques update this according to new Structure

//    public void registerProduct() throws IOException, SQLException {
//        ProductFormat product = (ProductFormat) request.getObject();
//
//        OutputStream output = this.socket.getOutputStream();
//        ObjectOutputStream objectOutput =  new ObjectOutputStream(output);
//
//        try {
//            Connection connection = Db.getConnection();
//            String sql = "INSERT INTO products(product_code,business_id,name,price,quantity,description,bonded_points,registered_by,created_at)" +
//                    " values(?,?,?,?,?,?,?,?,?)";
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setLong(1,product.getProductCode());
//            preparedStatement.setInt(2,product.getBusiness_id());
//            preparedStatement.setString(3,product.getName());
//            preparedStatement.setFloat(4,product.getPrice());
//            preparedStatement.setInt(5,product.getQuantity());
//            preparedStatement.setString(6,product.getDescription());
//            preparedStatement.setDouble(7,product.getBondedPoints());
//            preparedStatement.setInt(8,product.getRegistered_by());
//            preparedStatement.setString(9,product.getCreatedAt());
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
//                Response response = new Response(500,product);
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
        try{

            ProductFormat productFormat = new ProductFormat();

            //Open a connection
            conn = Db.getConnection();

            //Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT product_code,business_id,name,price,quantity,description,bonded_points,registered_by,created_at FROM products WHERE id= "+jsonNode.get("id").asText()+" ;";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                System.out.println("Reached Here 5");
                System.out.println("Sent ID: "+jsonNode.get("id").asText());

                productFormat.setProductCode(rs.getLong("product_code"));
                productFormat.setBusiness_id(rs.getInt("business_id"));
                productFormat.setName(rs.getString("name"));
                productFormat.setPrice(rs.getFloat("price"));
                productFormat.setQuantity(rs.getInt("quantity"));
                productFormat.setDescription(rs.getString("description"));
                productFormat.setBondedPoints(rs.getDouble("bonded_points"));
                productFormat.setRegistered_by(rs.getInt("registered_by"));
                productFormat.setCreatedAt(rs.getString("created_at"));
                System.out.println("Reached here 6");
            }

            System.out.println("Name: "+productFormat.getName());

            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
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

//
//    public void updateProduct() throws IOException, SQLException {
//        ProductFormat product = (ProductFormat) request.getObject();
//
//        OutputStream output = this.socket.getOutputStream();
//        ObjectOutputStream objectOutput =  new ObjectOutputStream(output);
//
//        Statement stmt = null;
//        Connection conn = null;
//
//        try {
//            conn = Db.getConnection();
//
//            System.out.println("Creating statement...");
//            stmt = conn.createStatement();
//
//            String sql = "UPDATE products SET product_code = "+product.getProductCode()+",business_id = "+product.getBusiness_id()+
//                    ",name="+product.getName()+",price="+product.getPrice()+",quantity="+product.getQuantity()+
//                    ",description = "+product.getDescription()+",bonded_points="+product.getBondedPoints()+
//                    ",registered_by = "+product.getRegistered_by()+",created_at = '2021-02-04' WHERE id = "+product.getId();
//
//            stmt.executeUpdate(sql);
//
////            if(preparedStatement.executeUpdate() > 0){
////                preparedStatement.executeUpdate();
//            List responseData = new ArrayList<>();
//            Response response = new Response(200,product);
//            responseData.add(response);
//
//            //Sending the response to client
//            objectOutput.writeObject(responseData);
////            }
////            else{
////                List responseData = new ArrayList<>();
////                Response response = new Response(400,product);
////                responseData.add(response);
////                //Sending the response to client
////                objectOutput.writeObject(responseData);
////            }
//            stmt.close();
//            conn.close();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            List responseData = new ArrayList<>();
//            Response response = new Response(500,new ProductFormat());
//            responseData.add(response);
//            //Sending the response to client
//            objectOutput.writeObject(responseData);
//        }
//    }

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

    // Jacques update this according to new Structure

//    public void getAllProducts() throws IOException, SQLException {
//        System.out.println("request to get products was received");
//        OutputStream output = this.socket.getOutputStream();
//        ObjectOutputStream objectOutput =  new ObjectOutputStream(output);
//
//        List products = null;
//        List response = new ArrayList<>();
//
//        try {
//            Statement statement = Db.getStatement();
//            ResultSet records = statement.executeQuery("SELECT * FROM products");
//
//            products = new ArrayList<ProductFormat>();
//
//            while (records.next()){
//                products.add(
//                        new ProductFormat(
//                                records.getInt("business_id"), records.getString("name"),
//                                records.getFloat("price"), records.getInt("quantity"),
//                                records.getString("description"),records.getDouble("bonded_points"),
//                                records.getInt("registered_by"), records.getString("created_at")
//                        )
//                );
//            }
//
//            response.add(new Response(200,products));
//            objectOutput.writeObject(response);
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            response.add(new Response(500,new Object()));
//            objectOutput.writeObject(response);
//        }
//    }

//    public void getProduct() throws IOException {
//        output = new DataOutputStream(this.socket.getOutputStream());
//        output.writeUTF("Single product with ID: ");
//    }
}