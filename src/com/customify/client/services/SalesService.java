package com.customify.client.services;

import com.customify.client.SendToServer;
import com.customify.client.data_format.SalesFormat;

import java.io.IOException;
import java.net.Socket;

public class SalesService {
    private Socket socket;

    public SalesService(Socket socket) {
        this.socket = socket;
    }

    public void getSaleById(String saleId){}

    public void deleteSale(String salesId){}

    public void getAllSales(String json) throws IOException {
        System.out.println(json);
        System.out.println("Getting all sales!");
        SendToServer sendToServer = new SendToServer(json,this.socket);
        if(sendToServer.send()){
            System.out.println("Boom! Done");
        }
    }

    public void create(SalesFormat salesFormat){}
}
