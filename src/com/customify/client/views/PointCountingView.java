package com.customify.client.views;

import com.customify.client.services.AuthService;
import com.customify.client.services.PointsService;
import com.customify.shared.requests_data_formats.LoginFormat;
import com.customify.shared.requests_data_formats.PointsByCustomerEmailFormat;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;

//author: Fredson
public class PointCountingView {
    private Socket socket;

    public PointCountingView(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void view() throws IOException {
        boolean points = true;
        Scanner scan = new Scanner(System.in);
        String choice;
        String customerEmail = null;
        PointsService pointsService = null;

        pointsLoop:do{
            System.out.println("------------------POINTS---------------------");
            System.out.println("\n         00. Return Home");
            System.out.println("         1. All Customers' Points");
            System.out.println("         2. Highest Points");
            System.out.println("         3. Points by customer");

            System.out.print("\n\n           Enter your choice: ");
            choice=scan.nextLine();

            if(choice.equals("00")) {
                break pointsLoop;
            }
            else if(choice.equals("1")) {

            }
            else if(choice.equals("2")){
                System.out.println("Not Done");
            }
            else if(choice.equals("3")){
                System.out.print("\n           Enter Customer Email: ");
                customerEmail = scan.nextLine();
                PointsByCustomerEmailFormat format = new PointsByCustomerEmailFormat(customerEmail);
                pointsService.getPointsByCustomerEmail(format);
            }
            else{
                System.out.println("Bad choice");
                points = false;
            }

            points = false;

//            LoginFormat format = new LoginFormat(email,password);
//            AuthService authService = new AuthService(socket);
//            authService.login(format);
        }while(points);
    }
}