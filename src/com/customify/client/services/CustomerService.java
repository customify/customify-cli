package com.customify.client.services;

import com.customify.client.SendToServer;
import com.customify.client.data_format.CreateCustomerFormat;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.Socket;

public class CustomerService {
    private Socket socket;
    public CustomerService(){}

    public  CustomerService(Socket socket)
    {this.socket = socket;}


    public void create(CreateCustomerFormat format) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(format);
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {
        }

    }

    public void update(){}
    public void disable(){}
    public void undisable(){}
    public void getAll(){}
    public void get(){}
}
