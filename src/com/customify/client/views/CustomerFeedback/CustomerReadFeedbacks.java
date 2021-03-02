package com.customify.client.views.CustomerFeedback;

/*
   *@author: NSENGIYUMVA GERSHOM
*/
import com.customify.client.SendToServer;
import com.customify.client.services.CustomerFeedbackService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.customify.client.Keys;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

public class CustomerReadFeedbacks {
    private Socket socket;
    private InputStream input;
    private ObjectInputStream objectInput;
    private String json_data;

    public CustomerReadFeedbacks(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket(){
        return socket;        
    }

    public void setSocket(){
        
    }

    public void GetFeedbacks() throws IOException, ClassNotFoundException {
        String json = "{ \"key\" : \"" + Keys.GET_ALL_FEEDBACKS + "\" }";
        CustomerFeedbackService feedService = new CustomerFeedbackService(socket);
        feedService.getAllCustomerFeedbacks(json);
    }
}

