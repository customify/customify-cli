/**
 * @author Fiston Nshimiyandinze
 * @role
 * For handling reports concerned with coupon
 * */
package com.customify.client.services.reports;

import com.customify.client.SendToServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

public class CouponReportService {
    private Socket socket;
    private InputStream input;
    private ObjectInputStream objectInput;
    private String json_data;
    private List<String> response;
    public CouponReportService(){}
    public CouponReportService(Socket socket) {
        this.socket = socket;
    }
    public void getCouponWeekly(String json) throws IOException, ClassNotFoundException {
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {

        } else {
            System.out.println("Request failed...");
        }
    }
    public void getCouponDaily(String json) throws IOException, ClassNotFoundException {
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {

        } else {
            System.out.println("Request failed...");
        }
    }
}
