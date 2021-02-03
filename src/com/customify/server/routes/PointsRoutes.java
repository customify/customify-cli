package com.customify.server.routes;

import com.customify.server.controllers.PointsController;
import com.customify.shared.Request;

import java.net.Socket;

//author: Fredson
public class PointsRoutes {
    Socket socket;
    PointsController pointsController;
    Request request;

    public PointsRoutes(Socket socket, Request request) {
        this.socket = socket;
        this.pointsController = new PointsController(this.socket,this.request);
    }
    public void getPointsByCustomer(){
        pointsController.getPointsByCustomerEmail();
    }
}
