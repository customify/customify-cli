package com.customify.server.routes;

import com.customify.server.controllers.BusinessController;

import java.io.IOException;
import java.net.Socket;

public class BusinessRoute {
    Socket socket;
    BusinessController businessController;
    public BusinessRoute(Socket socket) {
        this.socket = socket;
    }
    public BusinessRoute() {

    }
    public void  readBusinessRoute() throws IOException {
        businessController.getall();
    }

}
