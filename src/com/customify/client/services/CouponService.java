/*
* By Makuza Mugabo Verite
* client service: Manages all things related to client-server interaction
* */

package com.customify.client.services;

import com.customify.client.Colors;
import com.customify.client.SendToServer;
import com.customify.client.data_format.CouponFormat;
import com.customify.client.data_format.RedeemCoupon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

public class CouponService {

    Socket socket;
    private InputStream input;
    private ObjectInputStream objectInput;
    private String json;

    private CouponService() throws IOException {}

    public CouponService(Socket socket) throws IOException {
        this.socket = socket;
    }






    public void create(CouponFormat couponFormat){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(couponFormat);
            SendToServer sendToServer = new SendToServer(json,this.socket);
            if (sendToServer.send()){
                this.input = this.socket.getInputStream();
                this.objectInput = new ObjectInputStream(this.input);

                String data = (String) this.objectInput.readObject();
                System.out.println("\n");
                System.out.println(Colors.ANSI_BLUE);
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+data);
                System.out.println(Colors.ANSI_RESET);
                System.out.println("\n");
            }
        } catch (JsonProcessingException e) {
            System.out.println("Parsing create coupon "+e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void redeemCoupon(RedeemCoupon redeemCoupon){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            String json = objectMapper.writeValueAsString(redeemCoupon);
            SendToServer sendToServer = new SendToServer(json, this.socket);
          if(sendToServer.send()){
              this.input = this.socket.getInputStream();
              this.objectInput = new ObjectInputStream(this.input);

              String data = (String) this.objectInput.readObject();
              System.out.println("\n");
              System.out.println(Colors.ANSI_BLUE);
              System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+data);
              System.out.println(Colors.ANSI_RESET);
              System.out.println("\n");
          }
        }catch (JsonProcessingException e){
            System.out.println("Parsing redeem coupon "+e.getMessage());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getCoupons() throws IOException, ClassNotFoundException {
      String json  = "{ \"key\": \"GET_ALL_COUPONS\"}";
       SendToServer sendToServer = new SendToServer(json,this.socket);
     if(sendToServer.send()){
       this.handleGetResponse(json);
      }
    }

    public void handleGetResponse(String json) throws IOException, ClassNotFoundException {
        //Get response
        this.input = this.socket.getInputStream();
        this.objectInput = new ObjectInputStream(this.input);
        ObjectMapper objectMapper = new ObjectMapper();

        //Casting the response data to list
        List<String> data = (List<String>) this.objectInput.readObject();
       Iterator itr = data.iterator();

        String leftAlignFormat = "| %-13s | %-12s | %-12s | %-21s | %-21s | %-21s | %-13s |%n";

        System.out.format("\t\t\t\t\t\t\t\t\t\t\t\t\t+---------------+--------------+--------------+-----------------------+-----------------------+-----------------------+---------------+%n");
        System.out.format("\t\t\t\t\t\t\t\t\t\t\t\t\t| CouponID      | customerID   |  CouponCode  | Expire Date           |  created at           | coupon Status         | Coupon value  |%n");
        System.out.format("\t\t\t\t\t\t\t\t\t\t\t\t\t+---------------+--------------+--------------+-----------------------+-----------------------+-----------------------+---------------+%n");
        while (itr.hasNext()) {
            JsonNode jsonNode = objectMapper.readTree((String) itr.next());
            System.out.format("\t\t\t\t\t\t\t\t\t\t\t\t\t"+leftAlignFormat,jsonNode.get("coupon_id").textValue(),jsonNode.get("customer_id").textValue(),jsonNode.get("coupon_code").textValue(),jsonNode.get("created_at").textValue(),jsonNode.get("expire_date").textValue(),jsonNode.get("coupon_status").textValue(),jsonNode.get("coupon_value").textValue());
        }
        System.out.format("\t\t\t\t\t\t\t\t\t\t\t\t\t+---------------+--------------+--------------+-----------------------+-----------------------+-----------------------+---------------+%n");
        System.out.println("\n");

    }
}
