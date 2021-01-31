package com.customify.client.views;

import com.customify.client.services.Auth;
import com.customify.server.utils.PrepareJson;
import com.customify.shared.data_format.LoginFormat;
import com.customify.shared.data_format.SignUpFormat;

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

    public void view() throws IOException {
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

            // Set our data...
//            String meta = "firstName,lastName,email";
//            String values = firstName+","+lastName+","+email;
//            PrepareJson prepareJson =new PrepareJson(values,meta);


            SignUpFormat format = new SignUpFormat(email,lastName,firstName);
//
//            PrepareJson prepareJson =new PrepareJson(values,meta);
//            String json = prepareJson.getJson();

            Auth auth = new Auth(socket);
            auth.signUp(format);


        }while(signup);

    }
}
