/**
 * @author Fiston Nshimiyandinze
 * @role
 * For handling reports concerned with feedback
 * */
package com.customify.client.services.reports;

import com.customify.client.SendToServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

public class FeedbackReportService {
    private Socket socket;
    private InputStream input;
    private ObjectInputStream objectInput;
    private String json_data;
    private List<String> response;
    public FeedbackReportService(){}
    public FeedbackReportService(Socket socket) {
        this.socket = socket;
    }
    public void getFeedbackWeekly(String json) throws IOException, ClassNotFoundException {
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {

        } else {
            System.out.println("Request failed...");
        }
    }
    public void getFeedbackDaily(String json) throws IOException, ClassNotFoundException {
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {

        } else {
            System.out.println("Request failed...");
        }
    }
}
