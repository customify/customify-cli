// By Makuza Mugabo Verite on 02/03/2021
package com.customify.server.services;

import com.customify.server.CustomizedObjectOutputStream;
import com.customify.server.Db.Db;
import com.customify.server.response_data_format.sale.SaleDataFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesService {
  private Socket socket;
  private Connection connection = Db.getConnection();
  private SaleDataFormat saleDataFormat;
  ObjectMapper objectMapper = new ObjectMapper();
  ObjectOutputStream objectOutputStream;
  OutputStream outputStream;

    public SalesService(Socket socket) {
        this.socket = socket;
    }


    public void buyAProduct(String jsonData) throws IOException {

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonData);
            PointsService pointsService;

            String query = "INSERT INTO Sale(customerID, quantity, totalPrice, employeeID, productID) VALUES (?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(query);



            statement.setString(1,jsonNode.get("customerID").asText());
            statement.setInt(2,jsonNode.get("quantity").asInt());
            statement.setString(3,jsonNode.get("totalPrice").asText());
            statement.setString(4,jsonNode.get("employeeID").asText());
            statement.setString(5,jsonNode.get("productID").asText());
            statement.execute();

            SaleDataFormat saleDataFormat = new SaleDataFormat(jsonNode.get("customerID").asText(),jsonNode.get("quantity").asInt(),jsonNode.get("totalPrice").asText(),jsonNode.get("employeeID").asText(),jsonNode.get("productID").asText());

            PointsService.recordPointsAfterSale(saleDataFormat);

            this.sendToClient("Product sold!");

        }catch (JsonProcessingException e){
            this.sendToClient("Failed to parse request");
        } catch (IOException ioException) {
            this.sendToClient("Failed to send response");
        }catch (SQLException e){
            this.sendToClient("Invalid CustomerID");
            System.out.println(e.getMessage());
        }
    }

//    public void getAllSales() throws IOException {
//        List<String> sales = new ArrayList<>();
//        try{
//            String query = "SELECT * FROM Sale";
//
//            Statement statement = connection.createStatement();
//
//            ResultSet resultSet = statement.executeQuery(query);
//            while (resultSet.next()){
//                this.saleDataFormat = new SaleDataFormat(resultSet.getInt(1),resultSet.getString(2),(resultSet.getInt(3)),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6));
//                String jsonData = this.objectMapper.writeValueAsString(saleDataFormat);
//                sales.add(jsonData);
//            }
//        }catch (SQLException | JsonProcessingException e){
//            e.printStackTrace();
//            System.out.println("Error while sending to the client");
//        } finally {
//           this.sendToClient(sales);
//        }
//    }

    public void sendToClient(Object dataToSend) throws IOException {
        outputStream = socket.getOutputStream();
        this.objectOutputStream = new CustomizedObjectOutputStream(this.outputStream);
        objectOutputStream.writeObject(dataToSend);
    }
}
