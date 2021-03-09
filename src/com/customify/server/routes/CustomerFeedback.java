package com.customify.server.routes;

import com.customify.server.services.CustomerFeedbackService;

import java.io.IOException;
import java.net.Socket;

public class CustomerFeedback {
    Socket socket;

    CustomerFeedbackService CFService;
    public CustomerFeedback(Socket socket) {
        this.socket = socket;
    }
    public CustomerFeedback() {

    }

    public void getAllCustomerFeedbacks() throws IOException{
        CFService.getAllFeedbacks();
    }
}
