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
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

public class CustomerFeedbackService {
    private InputStream input;
    private ObjectInputStream objectInput;
    private String json_data;
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
            System.out.println("\nFeeback Sent\n");
        } else {
            System.out.println("Failed to send the feedback data!! You need to check well");
        }
    }

    // the function for getting all customer feedbacks
    public void getAllCustomerFeedbacks(String json) throws IOException, ClassNotFoundException {
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {
            this.getFeedbacks();
        } else {
            System.out.println("Request failed...");
        }
    }

    public void getFeedbacks() throws IOException, ClassNotFoundException {
        this.input = this.socket.getInputStream();
        this.objectInput = new ObjectInputStream(this.input);
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> data = (List<String>) this.objectInput.readObject();
        Iterator rs = data.iterator();

        System.out.println("\nAll customer Feedbacks\n");
        System.out.format("%5s%5s%15s%50s%12s\n", "CustomerID", "BusinessId", "Title", "Description", "CreatedD");
        System.out.println();
        ;
        while (rs.hasNext()) {
            JsonNode cf = objectMapper.readTree((String) rs.next());
            System.out.format("%5d%5d%15s%50s%12s\n", cf.get("customerId").asInt(), cf.get("businessId").asInt(),
                    cf.get("title").asText(), cf.get("description").asText(), cf.get("created_date").asText());
        }

    }

    // delete feedbacks

}
