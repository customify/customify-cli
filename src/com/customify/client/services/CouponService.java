package com.customify.client.services;

import com.customify.client.SendToServer;
import com.customify.client.data_format.CouponFormat;
import com.customify.client.data_format.RedeemCoupon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.Socket;

public class CouponService {

    Socket socket;

    public CouponService(Socket socket) {
        this.socket = socket;
    }

    public void create(CouponFormat couponFormat){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(couponFormat);
            SendToServer sendToServer = new SendToServer(json,this.socket);
            if (sendToServer.send()){
                //System.out.println("sen");
            }
        } catch (JsonProcessingException e) {
            System.out.println("Parsing create coupon "+e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void reedemCoupon(RedeemCoupon redeemCoupon){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            String json = objectMapper.writeValueAsString(redeemCoupon);
            SendToServer sendToServer = new SendToServer(json,this.socket);
        }catch (JsonProcessingException e){
            System.out.println("Parsing reedem coupon "+e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
