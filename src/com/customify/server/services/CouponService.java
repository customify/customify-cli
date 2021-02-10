// By Makuza Mugabo Verite on 8 Feb 2021
// Handles backend related functionality for coupon

package com.customify.server.services;

import com.customify.client.SendToServer;
import com.customify.server.Db.Db;
import com.customify.server.SendToClient;
import com.customify.server.response_data_format.CouponFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.*;
import java.util.Collections;
import java.util.Random;

public class CouponService {
  Socket socket;
  ObjectOutputStream objectOutputStream;


  public CouponService(Socket socket){
      this.socket = socket;
  }

  //TODO: add the functionality for the following methods
  public void coupingByProduct(String couponFormat) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = objectMapper.readTree(couponFormat);

    String code = this.generateCoupon(jsonNode.get("source").asInt());

    try{
      Connection connection = Db.getConnection();

      String query = "INSERT INTO Coupon(customer_id,coupon_code,created_at,expire_date,coupon_status,coupon_value)  VALUES(?,?,NOW(),?,?,?)";

      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1,jsonNode.get("customer_id").asText());
      statement.setString(2,code);
      statement.setString(3,jsonNode.get("expiry").asText());
      statement.setString(4,"NOT_USED");
      statement.setString(5,jsonNode.get("value").asText());

      if(statement.execute()){
        System.out.println("Failed");
      }else{
        System.out.println("coupon generated");
      }

      SendToServer sendToServer = new SendToServer(code,this.socket);
      sendToServer.send();

    }catch (SQLException | IOException e){
      System.out.println("Error while creating a new coupon");
      System.out.println(e.getMessage());
    }
  }

  public void coupingByCustomer(){}
  public void redeemCoupon(){}
  public void checkIfCouponIsValid(){}


  public void getAllCoupons(String jsonData){
    ObjectMapper objectMapper = new ObjectMapper();

    System.out.println("Getting coups "+jsonData);

    try{
      Statement  statement = Db.getStatement();
      ResultSet rs = statement.executeQuery("select * from Coupon");

      while (rs.next()){
        CouponFormat couponFormat = new CouponFormat(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
        String json = objectMapper.writeValueAsString(couponFormat);
        System.out.println(json);
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
}
