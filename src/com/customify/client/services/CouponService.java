package com.customify.client.services;

import com.customify.client.SendToServer;
import com.customify.client.data_format.CouponFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.Socket;

public class CouponService {

    Socket socket;

    public CouponService(Socket socket) {
        this.socket = socket;
    }

    public void create(String couponFormat){
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
}
