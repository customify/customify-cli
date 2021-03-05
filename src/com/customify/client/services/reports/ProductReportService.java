/**
 * @author Fiston Nshimiyandinze
 * @role
 * For handling reports concerned with products
 * */
package com.customify.client.services.reports;

import com.customify.client.SendToServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

public class ProductReportService {
    private Socket socket;
    private InputStream input;
    private ObjectInputStream objectInput;
    private String json_data;
    private List<String> response;
    public ProductReportService(){}
    public ProductReportService(Socket socket) {
        this.socket = socket;
    }
    public void getProductsWeekly(String json) throws IOException, ClassNotFoundException {
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {

        } else {
            System.out.println("Request failed...");
        }
    }
    public void getProductsDaily(String json) throws IOException, ClassNotFoundException {
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {

        } else {
            System.out.println("Request failed...");
        }
    }
}
