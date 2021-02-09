// By Makuza Mugabo Verite on 8 Feb 2021
// Handles backend related functionality for coupon

package com.customify.server.services;

import com.customify.client.data_format.CouponFormat;

import java.net.Socket;
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
  public void coupingByCustomer(){}
  public void redeemCoupon(){}
  public void checkIfCouponIsValid(){}

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
