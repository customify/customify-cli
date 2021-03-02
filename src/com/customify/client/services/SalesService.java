package com.customify.client.services;

import com.customify.client.data_format.SalesFormat;

import java.net.Socket;

public class SalesService {
    private Socket socket;

    public SalesService(Socket socket) {
        this.socket = socket;
    }

    public void getSaleById(String saleId){}

    public void deleteSale(String salesId){}

    public void getAllSales(){
        System.out.println("Getting all sales!");
    }

    public void create(SalesFormat salesFormat){}

}
