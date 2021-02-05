package com.customify.server.routes;

import com.customify.server.controllers.BussinessController;

import java.io.IOException;
import java.net.Socket;

public class BusinessRoute {
    Socket socket;
    BussinessController bussController;
    public BusinessRoute(Socket socket) {
        this.socket = socket;
    }
    public BusinessRoute() {

    }
    public void  readBusinessRoute() throws IOException {
        bussController.getall();
    }

}
