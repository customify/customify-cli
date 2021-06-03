/**
 * Author: Niyonzima Stecie
 * done on: 4 Feb 2021
 *
 * This is is the service class for the customers to provide the feedbacks
 * for the services they got from various businesses.
 *
 * */
package com.customify.client.services;

import com.customify.client.Colors;
import com.customify.client.SendToServer;
import com.customify.client.data_format.CustomerFeedback.CustomerFeedbackDataFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

public class CustomerFeedbackService {
    private Socket socket;
    private InputStream input;
    private ObjectInputStream objectInput;
    private String json_data;

    public CustomerFeedbackService(Socket socket) {
        this.socket = socket;
    }

    /**
     * @author NIYONZIMA Stecie
     * This is the function for sending customer feedbac data to the server.
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

    //the function for reading customers
    public void getAllCustomerFeedbacks(String json) throws IOException, ClassNotFoundException {
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {
             this.getFeedbacks();
             System.out.println("Provided feedbacks");
        } else {
            System.out.println("Request failed...");
        }
    }
    // --------------------------------------------------------------------------------------------------------
   //for displaying the data from the server
    public void getFeedbacks() throws IOException, ClassNotFoundException {
        this.input = this.socket.getInputStream();
        this.objectInput = new ObjectInputStream(this.input);
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> data = (List<String>) this.objectInput.readObject();
        Iterator rs = data.iterator();

        System.out.println(Colors.ANSI_CYAN);
        System.out.println("\n\t\t\t\t\t\t\t\t\tAll provided Feedbacks");
        System.out.println(Colors.ANSI_RESET);

        System.out.println(Colors.ANSI_GREEN);
        System.out.format("%5s%35s%35s%35s%35s\n", "CustomerID", "BusinessId", "Title", "Description", "CreatedD");
        System.out.println(Colors.ANSI_RESET);
        ;
            while (rs.hasNext()) {
                JsonNode cf = objectMapper.readTree((String) rs.next());
                System.out.format("%5d%35d%35s%35s%35s\n", cf.get("customerId").asInt(), cf.get("businessId").asInt(),
                        cf.get("title").asText(), cf.get("description").asText(), cf.get("creationDate").asText());
            }
    }

   // function to delete customer feedback
    public void deleteCustomerFeedback(String json) throws IOException, ClassNotFoundException {
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {
            handleResponse("deleteCustomerFeedback");
        } else {
            System.out.println("An error occured");
        }
    }

    // this is the function for providing appropriate response to the calling
    // function
    public void handleResponse(String func_name) throws ClassNotFoundException {
        try {
            this.input = this.socket.getInputStream();
            this.objectInput = new ObjectInputStream(this.input);
            this.json_data = (String) this.objectInput.readObject();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json_data);
            System.out.println(jsonNode);
            switch (func_name) {
                case "getAllFeedbacks":
                    this.getFeedbacks();
                    break;
                case "deleteCustomerFeedback":
                    if (jsonNode.get("statusCode").asInt() == 200)
                        System.out.println("The feedback removed away. ");
                    else System.out.println("An error occurred");
                    break;
                default:
                    System.out.println("NO SUCH CHOICE PLEASE !!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}