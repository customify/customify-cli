/*
  Class to signup the login view
*/
package com.customify.client.views;

import com.customify.client.services.AuthService;
import com.customify.shared.requests_data_formats.SignUpFormat;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SignupView {

    private Socket socket;

    public SignupView(Socket socket){
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
        String firstName,lastName,email;
        System.out.println("------------------SIGNUP---------------------");
        boolean signup = true;

        signupLoop:do{
            System.out.println("\n         00.Back");

            System.out.print("\n           Enter Firstname: ");
            firstName=scan.nextLine();
            if(firstName.equals("00"))
                break signupLoop;

            System.out.print("\n           Enter Lastname: ");
            lastName=scan.nextLine();

            if(lastName.equals("00"))
                break signupLoop;

            System.out.print("\n           Enter Email: ");
            email=scan.nextLine();

            if(email.equals("00"))
                break signupLoop;
            SignUpFormat format = new SignUpFormat(email,lastName,firstName);
            AuthService authService = new AuthService(socket);
            authService.signUp(format);
        }while(signup);

    }
}
