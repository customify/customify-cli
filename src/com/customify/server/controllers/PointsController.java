package com.customify.server.controllers;

import com.customify.server.services.AuthService;
import com.customify.shared.Request;
import com.customify.shared.requests_data_formats.PointsByCustomerEmailFormat;

import java.net.Socket;

public class PointsController {
    private Socket socket;
    private Request request;

    public PointsController(Socket socket, Request request) {
        this.socket = socket;
        this.request = request;
    }

    public void getPointsByCustomerEmail(){
        PointsByCustomerEmailFormat data = (PointsByCustomerEmailFormat) request.getObject();
        System.out.println("Customer email: "+data.getEmail());
    }
}