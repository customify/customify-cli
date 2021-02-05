package com.customify.client.services;

/**
 * Author: Niyonzima Stecie
 * done on: 4 Feb 2021
 * 
 * This is is the service class for the customers to provide the feedbacks
 * for the services they got.
 * 
 * */

import com.customify.shared.Keys;
import com.customify.client.Common;
import com.customify.shared.Request;
import com.customify.shared.Response;
import com.customify.shared.responses_data_format.AuthFromats.FeedbackSuccessFormat;
import com.customify.shared.requests_data_formats.FeedbackFormat;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

public class FeedbackService {
    private Socket socket;
    private String data;
    InputStream inputStream;
    ObjectInputStream objectInputStream;

    public FeedbackService(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) throws IOException {
        this.socket = socket;
        inputStream = this.socket.getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);
    }

    /**
     * This method is for tracking the choice of the customer(feedback) and 
     * then this choice can be sent to the server
     * */ 
    public void Comment(FeedbackFormat format) throws IOException, ClassNotFoundException {
        Request request = new Request(Keys.FEEDBACK, format);
        Common common = new Common(request, this.socket);
        if (common.sendToServer()) {
            this.handleFeedbackResponse();
        }
    }

    public void handleFeedbackResponse() throws IOException, ClassNotFoundException {
        inputStream = this.socket.getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);
        List<Response> response = (List<Response>) objectInputStream.readObject();
        if (response.get(0).getStatusCode() == 200) {
            FeedbackSuccessFormat data = (FeedbackSuccessFormat) response.get(0).getData();
            System.out.println("              Feedback successfully sent");
        }
    }
}
