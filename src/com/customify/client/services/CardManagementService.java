package com.customify.client.services;

import com.customify.client.Common;
import com.customify.shared.Keys;
import com.customify.shared.Request;
import com.customify.shared.requests_data_formats.CardDataFormat;

import java.io.IOException;
import java.net.Socket;

public class CardManagementService {
    Socket socket;
    CardDataFormat dataFormat;

    public CardDataFormat getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(CardDataFormat dataFormat) {
        this.dataFormat = dataFormat;
    }

    public CardManagementService(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void disableCard(CardDataFormat data) throws IOException {
        Request request = new Request(Keys.DISABLE_CARD,data);

        Common common = new Common(request,this.socket);

        if (common.sendToServer()){
            System.out.println("card was disabled successfully");
        }
        else{
            System.out.println("Error occurred when sending request");
        }
    }


}
