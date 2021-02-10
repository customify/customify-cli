package com.customify.server.services;

import com.customify.shared.requests_data_formats.LoginFormat;

import java.net.Socket;

public class AuthService {
    private Socket socket;

    public AuthService(Socket socket) {
        this.socket = socket;
    }
    public void login(LoginFormat loginFormat){

    }
}
