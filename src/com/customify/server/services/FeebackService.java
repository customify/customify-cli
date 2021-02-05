package com.customify.server.services;

/**
 * Author: NSENGIYUMVA GERSHOM
 * done on 3 Feb 2021
 * 
 * This class is for formatting the data to be sent into the 
 * the desired method
 * */
import com.customify.shared.Response;
import com.customify.shared.requests_data_formats.FeedbackFormat;
import com.customify.shared.responses_data_format.AuthFromats.FeedbackSuccessFormat;

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
        this.statusCode = 200;

        FeedbackSuccessFormat sformat = new FeedbackSuccessFormat(format.getTitle());
        response = new Response(statusCode, sformat);
        responseData.add(response);

        objectOutput.writeObject(responseData);
    }
}
