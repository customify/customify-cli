// By Makuza Mugabo Verite on 8 Feb 2021
// Handles backend related functionality for coupon

package com.customify.server.services;


import com.customify.server.CustomizedObjectOutputStream;
import com.customify.server.Db.Db;
import com.customify.server.SendToClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.*;
import java.util.Collections;
import java.util.Random;

public class CouponService {
  Socket socket;
  private Connection connection = Db.getConnection();
  ObjectMapper objectMapper = new ObjectMapper();
  ObjectOutputStream objectOutputStream;
  OutputStream outputStream;

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

  public void redeemCoupon(){}

  public void checkIfCouponIsValid(){}


  public void getAllCoupons(String jsonData) throws SQLException {
    try{
      Statement  statement = Db.getStatement();
      ResultSet rs = statement.executeQuery("SELECT * FROM Coupon");
      while (rs.next()){
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonCoupons =  objectMapper.writeValueAsString(rs);
        SendToClient sendToClient = new SendToClient(this.socket, Collections.singletonList(jsonCoupons));
       // System.out.println(rs.getInt(1)+" "+rs.getInt(2)+" "+rs.getString(3)+" "+rs.getDate(4)+" "+rs.getDate(5)+" "+rs.getString(6)+" "+rs.getString(7));
      }
    }catch (SQLException | JsonProcessingException e){
      System.out.println("Sql error: "+e.getMessage());
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
