package com.customify.client.services;

import com.customify.client.Keys;
import com.customify.client.SendToServer;
import com.customify.client.data_format.billing.GetFeaturesFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

public class BillingService {
    Socket socket;

    public BillingService(){}
    public BillingService(Socket socket){
        this.socket = socket;
    }

    public void getFeatures() throws IOException {
        GetFeaturesFormat format = new GetFeaturesFormat(Keys.GET_FEATURES);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        String json = objectMapper.writeValueAsString(format);
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {
            this.handleGetFeaturesRes();
        }
    }

    public void handleGetFeaturesRes(){
        try{
            InputStream inputStream = this.socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            List<String> response = (List<String>) objectInputStream.readObject();

            String json_response = response.get(0);
            System.out.println("HERE'S THE RESPONSE FROM THE SERVER => " + json_response);

        }catch(Exception e) {
            System.out.println("RESPONSE ERROR =>"+e.getMessage());
        }
    }
}
