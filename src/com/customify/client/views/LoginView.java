package com.customify.client.views;
import com.customify.client.services.Auth;
import com.customify.server.utils.PrepareJson;
import com.customify.shared.data_format.LoginFormat;

import java.io.IOException;
import java.net.Socket;
import java.util.*;
public class LoginView {

    private Socket socket;

    public LoginView(Socket socket){
        this.socket = socket;
    }
    public Socket getSocket()
    {
        return socket;
    }
    public void setSocket(Socket socket){
        this.socket = socket;
    }
    public void view() throws IOException {

        boolean login = true;
        Scanner scan = new Scanner(System.in);
        String email,password;

        loginLoop:do{
            System.out.println("------------------LOGIN---------------------");
            System.out.println("\n         00. Return Home");
            System.out.println("         1. Signup");
            System.out.print("\n\n           Enter Email: ");
            email=scan.nextLine();
            if(email.equals("00"))
                break loginLoop;

            System.out.print("\n           Enter Password: ");
            password = scan.nextLine();
            login = false;

            LoginFormat format = new LoginFormat(email,password);
//
//            PrepareJson prepareJson =new PrepareJson(values,meta);
//            String json = prepareJson.getJson();

            Auth auth = new Auth(socket);
            auth.login(format);
        }while(login);

    }

}
