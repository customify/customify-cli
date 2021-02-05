package com.customify.server.controllers;

import com.customify.shared.requests_data_formats.CardDataFormat;

import java.net.Socket;
import java.sql.PreparedStatement;

public class CardManagementController {
    Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    public void disableCard(CardDataFormat dataFormat){

    }
}
