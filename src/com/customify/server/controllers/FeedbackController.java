package com.customify.server.controllers;

/**
 * Controller for customer feedback
 * */

import com.customify.server.services.FeebackService;
import com.customify.shared.Request;
import com.customify.shared.requests_data_formats.FeedbackFormat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class FeedbackController {
    DataOutputStream output;
    private Socket socket;
    private Request request;
    private FeebackService fService;

    public FeedbackController(Socket socket, Request request) throws IOException {
        this.socket = socket;
        this.request = request;
        this.fService = new FeebackService(this.socket);
    }

    public void feedbackOnServer() throws IOException, ClassNotFoundException {
        FeedbackFormat format = (FeedbackFormat) request.getObject();
        System.out.println("FEEDBACK DATA");
        System.out.println("Customer ID - " + format.getCustomerId());
        System.out.println("Business ID - " + format.getBusinessId());
        System.out.println("Title - " + format.getTitle());
        System.out.println("Description - " + format.getDescription());

        /*
         * Call the feedback service to handle the request
         */
        fService.Feed(format);

    }

    public void feedbackError() throws IOException {
        output = new DataOutputStream(socket.getOutputStream());
        output.writeUTF("Feedback provision can not work");
    }
}
