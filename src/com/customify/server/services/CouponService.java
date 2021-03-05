// By Makuza Mugabo Verite on 8 Feb 2021
// Handles backend related functionality for coupon

package com.customify.server.services;


import com.customify.server.response_data_format.CouponFormat;
import com.customify.server.CustomizedObjectOutputStream;
import com.customify.server.Db.Db;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CouponService {
  Socket socket;
  private Connection connection = Db.getConnection();
  ObjectMapper objectMapper = new ObjectMapper();
  ObjectOutputStream objectOutputStream;
  OutputStream outputStream;
  private CouponFormat couponFormat;

  public CouponService(Socket socket){
      this.socket = socket;
  }

  //TODO: add the functionality for the following methods
  public void coupingByProduct(String couponFormat){
    System.out.println("Coupling the product"+couponFormat);
  }

  public void createCoupon(String jsonData) throws IOException {

    try{
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode jsonNode = objectMapper.readTree(jsonData);


      String query = "INSERT INTO Coupon(customer_id,coupon_code,expire_date,coupon_value) VALUES (?,?,?,?)";
      String coupon = generateCoupon(jsonNode.get("source").asInt());

      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1,jsonNode.get("customer_id").asText());
      preparedStatement.setString(2,coupon);
      preparedStatement.setString(3,jsonNode.get("expiry").asText());
      preparedStatement.setString(4,jsonNode.get("value").asText());
      preparedStatement.execute();
      this.sendToClient("Coupon Generated: "+coupon);
    }catch (SQLException e){
      this.sendToClient("CustomerID is invalid");
    }catch (IOException e){
      e.printStackTrace();
      this.sendToClient("Something went wrong!");
    }
  }

  public void redeemCoupon(String jsonData) throws IOException {
    try{
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode jsonNode = objectMapper.readTree(jsonData);

      String query = "SELECT * FROM Coupon WHERE customer_id=? AND coupon_code=? AND coupon_status = 'NOT_USED'";

      PreparedStatement preparedStatement = connection.prepareStatement(query);

      preparedStatement.setString(1,jsonNode.get("customerID").asText());

      preparedStatement.setString(2,jsonNode.get("coupon_code").asText());

      ResultSet resultSet = preparedStatement.executeQuery();

      int id = 0;
      String couponValue = null;

      while (resultSet.next()){
        id = resultSet.getInt(1);
        couponValue =  resultSet.getString(7);
      }

      if(id == 0 || couponValue == null){
        this.sendToClient("Invalid Coupon");
        return;
      }


     PreparedStatement updateStatement = connection.prepareStatement("UPDATE  Coupon SET coupon_status = 'USED' WHERE coupon_id = ?");

      updateStatement.setInt(1,id);

      updateStatement.execute();

     this.sendToClient("Coupon "+jsonNode.get("coupon_code").asText()+" with value "+couponValue+" has been redeemed");

    } catch (SQLException | IOException e) {
      System.out.println(e.getMessage());
      this.sendToClient("Invalid Coupon");
     }
  }



  public void getAllCoupons(String jsonData) throws SQLException {
    try{
      String query = "SELECT * FROM Coupon";

      Statement statement = connection.createStatement();

      ResultSet resultSet = statement.executeQuery(query);
      List<String> coupons = new ArrayList();
      ObjectMapper objectMapper = new ObjectMapper();

      while (resultSet.next()){
        this.couponFormat = new CouponFormat(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7));
        String jsonCoupons =  objectMapper.writeValueAsString(this.couponFormat);
        coupons.add(jsonCoupons);
      }
      this.sendToClient(coupons);
    }catch (SQLException | JsonProcessingException e){
      System.out.println("Sql error: "+e.getMessage());
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }

  private String generateCoupon(int source){

    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    // create random string builder
    StringBuilder sb = new StringBuilder();

    // create an object of Random class
    Random random = new Random();

    // specify length of random string
    int length = 7;

    for(int i = 0; i < length; i++) {

      // generate random index number
      int index = random.nextInt(alphabet.length());


      char randomChar = alphabet.charAt(index);

      sb.append(randomChar);
    }

    return source+sb.toString();
  }

  public void sendToClient(Object dataToSend) throws IOException {
    outputStream = socket.getOutputStream();
    this.objectOutputStream = new CustomizedObjectOutputStream(this.outputStream);
    objectOutputStream.writeObject(dataToSend);
  }
}
