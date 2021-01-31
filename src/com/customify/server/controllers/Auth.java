package com.customify.server.controllers;

import com.customify.shared.Request;
import com.customify.shared.data_format.LoginFormat;
import com.customify.shared.data_format.SignUpFormat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Auth {
    DataOutputStream output;
private Socket socket;
private Request request;

    public  Auth(Socket socket, Request request)
    {
        this.socket = socket;
        this.request = request;
    }

    public void login()throws IOException{
//        output = new DataOutputStream(socket.getOutputStream());
        LoginFormat format =(LoginFormat)request.getObject();
//        output.writeUTF("You success fully logged in");
        System.out.println("LOGGED IN SUCESSFULLY ");
        System.out.println("Your Credentials");
        System.out.println("Email - "+format.getEmail());
        System.out.println("Password - "+format.getPassword());
    }

    public void loginError() throws IOException {
        output = new DataOutputStream(socket.getOutputStream());
        output.writeUTF("Login failed");
    }
    public void signup()throws IOException{
        //        output = new DataOutputStream(socket.getOutputStream());
        SignUpFormat format =(SignUpFormat)request.getObject();
//        output.writeUTF("You success fully logged in");
        System.out.println("LOGGED IN SUCESSFULLY ");
        System.out.println("\t\t\t\tYOUR REGISTRATION DETAILS");
        System.out.println("Email - "+format.getEmail());
        System.out.println("First Name - "+format.getFirstName());
        System.out.println("Last Name - "+format.getLastName());

    }
}
