// By Makuza Mugabo Verite on 8 Feb 2021
// Handles backend related functionality for coupon

package com.customify.server.services;

import com.customify.client.data_format.CouponFormat;
import com.customify.server.Db.Db;
import com.customify.server.SendToClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Random;

public class CouponService {
  Socket socket;

  public CouponService(Socket socket){
      this.socket = socket;
  }

  //TODO: add the functionality for the following methods
  public void coupingByProduct(String couponFormat){
    System.out.println("Couponing the product"+couponFormat);
  }
  public void coupingByCustomer(){

  }
  public void redeemCoupon(){}
  public void checkIfCouponIsValid(){}


  public void getAllCoupons(String jsonData){
    try{
      Statement  statement = Db.getStatement();
      ResultSet rs = statement.executeQuery("select * from Coupon");
      ObjectMapper objectMapper = new ObjectMapper();
      String jsonCoupons =  objectMapper.writeValueAsString(rs);
      SendToClient sendToClient = new SendToClient(this.socket, Collections.singletonList(jsonCoupons));
      while (rs.next()){
        System.out.println(rs.getInt(0)+" "+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getDate(3)+" "+rs.getDate(4)+" "+rs.getString(5)+" "+rs.getString(6));
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
