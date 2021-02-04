package com.customify.client.services;

/**
 * client feed back ser
 * */ 

import com.customify.shared.Keys;
import com.customify.client.Common;
import com.customify.shared.Request;
import com.customify.shared.Response;
import com.customify.shared.responses_data_format.AuthFromats.SuccessLoginFormat;
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
            SuccessLoginFormat data = (SuccessLoginFormat) response.get(0).getData();
            System.out.println("              Feedback successfully sent");
        }
    }
}
