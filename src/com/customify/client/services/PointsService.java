package com.customify.client.services;

import com.customify.client.Common;
import com.customify.shared.Keys;
import com.customify.shared.Request;
import com.customify.shared.requests_data_formats.PointsByCustomerEmailFormat;

import java.io.IOException;
import java.net.Socket;

/**
 * @author GISA KAZE Fredson
 * */
public class PointsService {
    private Socket socket;
    private Common common;

    public PointsService(Socket socket) {
        this.socket = socket;
    }

    /**
     * @author GISA KAZE Fredson
     * */
    public void getPointsByCustomerEmail(PointsByCustomerEmailFormat format) throws IOException {
        Request request = new Request(Keys.POINTS_BY_CUSTOMER_EMAIL, format);
        this.common = new Common(request, this.socket);
        if(common.sendToServer()){
            System.out.println("Request Sent successfully");
//            System.out.println(data.);
        }
    }
}