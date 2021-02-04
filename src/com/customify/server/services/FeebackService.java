package com.customify.server.services;

import com.customify.shared.Response;
import com.customify.shared.requests_data_formats.FeedbackFormat;
import com.customify.shared.responses_data_format.AuthFromats.SuccessLoginFormat;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class FeebackService {
    Socket socket;
    OutputStream output;
    ObjectOutputStream objectOutput;
    private int statusCode;
    Response response;
    List responseData = new ArrayList<>();

    public FeebackService(Socket socket) throws IOException {
        this.socket = socket;
        this.output = socket.getOutputStream();
        this.objectOutput = new ObjectOutputStream(output);
    }

    public void Feed(FeedbackFormat format) throws IOException {
        //setting the response status code
        this.statusCode = 200;
 
        //formatting the response into a successLoginFormat
        SuccessLoginFormat sformat = new SuccessLoginFormat(format.getTitle());
        response = new Response(statusCode,sformat);
        responseData.add(response);
 
        //Sending the response to server after it has been formated
        objectOutput.writeObject(responseData);
    }
}
