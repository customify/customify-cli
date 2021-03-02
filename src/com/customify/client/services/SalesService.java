package com.customify.client.services;

import com.customify.client.SendToServer;
import com.customify.client.data_format.SalesFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

public class SalesService {
    private Socket socket;

    public SalesService(Socket socket) {
        this.socket = socket;
    }

    public void getSaleById(String saleId){}

    public void deleteSale(String salesId){}

    public void getAllSales(String json) throws IOException {
        System.out.println(json);
        SendToServer sendToServer = new SendToServer(json,this.socket);
        if(sendToServer.send()){
            this.handleResponse();
        }
    }

    public void create(SalesFormat salesFormat){}

    private void handleResponse(){

        try{
            InputStream inputStream = this.socket.getInputStream();
            ObjectInputStream  objectInputStream = new ObjectInputStream(inputStream);
            //ObjectMapper objectMapper = new ObjectMapper();
            //JsonNode jsonNode = objectMapper.readTree(objectInputStream);
        }catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
