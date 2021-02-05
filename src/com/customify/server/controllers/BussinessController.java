package com.customify.server.controllers;

import com.customify.server.services.BussinessService;
import com.customify.shared.Request;

import java.io.IOException;
import java.net.Socket;

public class BussinessController {
    private Socket socket;
    private Request request;
    private BussinessService bussService;

    public BussinessController(Socket socket, Request request) throws IOException {
        this.socket = socket;
        this.request = request;
        this.bussService = new BussinessService(this.socket);
    }
    public void getall()throws IOException{
        bussService.getAll();
    }

}
