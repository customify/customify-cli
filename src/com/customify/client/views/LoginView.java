/*
  Class to display the login view
*/

package com.customify.client.views;
import com.customify.client.services.AuthService;
import com.customify.shared.requests_data_formats.LoginFormat;

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
    public void view() throws IOException, ClassNotFoundException {

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
            AuthService authService = new AuthService(socket);
            authService.login(format);
        }while(login);

    }

}
