package com.customify.client.services.billing;

import com.customify.client.SendToServer;
import com.customify.client.data_format.billing.PlanFormatClient;
import com.customify.server.response_data_format.billing.PlanFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.customify.client.Keys;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class PlanService {
    Socket socket = new Socket();
    InputStream inputStream;
    ObjectInputStream objectInputStream;
    private String response;

    public PlanService(){}
    public PlanService(Socket socket){
        this.socket = socket;
    }

    public void setResponse(String response) {
        this.response = response;
    }
    public String getResponse() {
        return this.response;
    }

    public void createPlan(PlanFormatClient planFormat) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(planFormat);
        SendToServer sendToServer = new SendToServer(json, socket);
        if(sendToServer.send()){
            inputStream = this.socket.getInputStream();
            objectInputStream = new ObjectInputStream(objectInputStream);
            this.setResponse((String) objectInputStream.readObject());
            System.out.println(this.getResponse());

        }else{
            System.out.println("Request failed");
        }
    }
    public void updatePlan(PlanFormat planFormat) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(planFormat);
        SendToServer sendToServer = new SendToServer(json, socket);
        if(sendToServer.send()){
            inputStream = this.socket.getInputStream();
            objectInputStream = new ObjectInputStream(objectInputStream);
            this.setResponse((String) objectInputStream.readObject());
            System.out.println(this.getResponse());
        }else{
            System.out.println("Request failed");
        }
    }
    public void getAllPlan(Keys key) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(key);
        SendToServer sendToServer = new SendToServer(json, socket);
        if(sendToServer.send()){
            inputStream = this.socket.getInputStream();
            objectInputStream = new ObjectInputStream(objectInputStream);
            List<PlanFormat> response = (List<PlanFormat>) objectInputStream.readObject();
            System.out.println("Available Plans");
            for(;;){
                System.out.println("PlanId:\t");
            }

        }
    }



}
