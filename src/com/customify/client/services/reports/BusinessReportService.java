/**
 * @author Fiston Nshimiyandinze
 * @role
 * For handling reports concerned with business on client side
 * */
package com.customify.client.services.reports;

import com.customify.client.SendToServer;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

public class BusinessReportService {
    private Socket socket;
    private InputStream input;
    private ObjectInputStream objectInput;
    private String json_data;
    private List<String> response;
    BusinessReportService(){}
    public BusinessReportService(Socket socket) {
        this.socket = socket;
    }

    public void getBlssWeekly(String json) throws IOException, ClassNotFoundException {
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {

        } else {
            System.out.println("Request failed...");
        }
    }
    public void getBlssDaily(String json) throws IOException, ClassNotFoundException {
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {

        } else {
            System.out.println("Request failed...");
        }
    }
}
