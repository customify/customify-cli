package com.customify.server.controllers;

import com.customify.server.services.AuthService;
import com.customify.shared.Request;
import com.customify.shared.requests_data_formats.LoginFormat;
import com.customify.shared.requests_data_formats.SignUpFormat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class AuthController {
    DataOutputStream output;
private Socket socket;
private Request request;
private AuthService authService;
    public AuthController(Socket socket, Request request) throws IOException {
        this.socket = socket;
        this.request = request;
        this.authService = new AuthService(this.socket);
    }

    public void login()throws IOException{
        LoginFormat format =(LoginFormat)request.getObject();
        System.out.println("LOGGED IN SUCESSFULLY ");
        System.out.println("Your Credentials");
        System.out.println("Email - "+format.getEmail());
        System.out.println("Password - "+format.getPassword());

        /*
         Call the authService to handle the request
        */
        authService.login(format);

    }

    public void loginError() throws IOException {
        output = new DataOutputStream(socket.getOutputStream());
        output.writeUTF("Login failed");
    }
    public void signup()throws IOException{
        SignUpFormat format =(SignUpFormat)request.getObject();
        System.out.println("LOGGED IN SUCESSFULLY ");
        System.out.println("\t\t\t\tYOUR REGISTRATION DETAILS");
        System.out.println("Email - "+format.getEmail());
        System.out.println("First Name - "+format.getFirstName());
        System.out.println("Last Name - "+format.getLastName());

    }
}
