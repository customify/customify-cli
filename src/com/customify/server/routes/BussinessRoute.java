package com.customify.server.routes;

import com.customify.server.controllers.BussinessController;

import java.io.IOException;
import java.net.Socket;

public class BussinessRoute {
    Socket socket;
    BussinessController bussController;
    public BussinessRoute(Socket socket) {
        this.socket = socket;
    }
    public BussinessRoute() {

    }
    public void  getAllRoute() throws IOException {
        bussController.getall();
    }

}
