package com.customify.server.routes;

import com.customify.server.services.BusinessService;

import java.io.IOException;
import java.net.Socket;

public class BusinessRoute {
    Socket socket;
    BusinessService businessService;
    public BusinessRoute(Socket socket) {
        this.socket = socket;
    }
    public BusinessRoute() {

    }
    public void getAllBusinesses() throws IOException{
        businessService.getAll();
    }


}
