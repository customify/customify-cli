package com.customify.server.routes;

import com.customify.server.services.BusinessService;
import com.customify.server.services.CustomerService;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

/**
 * @author Murenzi Confiance Tracy
 * @role
 * this is the customer route for the disable card
 * */

public class CustomerRoute {
    Socket socket;
    CustomerService customerService;
    public CustomerRoute(Socket socket) {
        this.socket = socket;
    }
    public CustomerRoute() {

    }
    public void disableCustomer() throws IOException, SQLException {
        customerService.disable();
    }
}
