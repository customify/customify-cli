/**
 * Author: Niyonzima Stecie
 * done on: 4 Feb 2021
 * 
 * This is is the service class for the customers to provide the feedbacks
 * for the services they got from various businesses.
 * 
 * */
package com.customify.client.services;

import com.customify.client.SendToServer;
import com.customify.client.data_format.CustomerFeedback.CustomerFeedbackDataFormat;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

public class CustomerFeedbackService {
    private final Socket socket;

    public CustomerFeedbackService(Socket socket) {
        this.socket = socket;
    }

    /**
     * @author NIYONZIMA Stecie
     */
    public void Feedback(CustomerFeedbackDataFormat format) throws IOException, ClassNotFoundException {
        var mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(format);
        SendToServer sendToServer = new SendToServer(json, this.socket);
        if (sendToServer.send()) {
            System.out.println("\nFeeback Sent");
        } else {
            System.out.println("Failed to send the feedback data!! You need to check well");
        }
    }
    // delete the feedback from the database

    // get the response from the server confirming the flow of data
    public void handleFeedbackResponse() throws IOException, ClassNotFoundException {
        InputStream inputStream = this.socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        List<Response> response = (List<Response>) objectInputStream.readObject();

        if (response.get(0).getStatusCode() == 201) {
            System.out.println("All done. The feedback has been sent.");
        }
    }
}
