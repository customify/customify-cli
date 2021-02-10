package com.customify.client.views;

import com.customify.client.services.AuthService;
import com.customify.client.services.PointsService;
import com.customify.shared.requests_data_formats.LoginFormat;
import com.customify.shared.requests_data_formats.PointsByCustomerEmailFormat;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author GISA KAZE Fredson
 * */
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

    /**
     * @author GISA KAZE Fredson
     * */
    public void view() throws IOException {
        boolean points = true;
        Scanner scan = new Scanner(System.in);
        String choice;
        String customerEmail = null;

        pointsLoop:do{
            System.out.println("------------------POINTS---------------------");
            System.out.println("\n         00. Return Home");
            System.out.println("         1. Winners");
            System.out.println("         2. Points by customer");

            System.out.print("\n\n           Enter your choice: ");
            choice=scan.nextLine();

            if(choice.equals("00")) {
                break pointsLoop;
            }
            else if(choice.equals("1")) {
                PointsService pointsService = new PointsService(this.socket);
                pointsService.getWinners();

            }
            else if(choice.equals("2")){
                System.out.print("\n           Enter Customer Email: ");
                customerEmail = scan.nextLine();
                PointsByCustomerEmailFormat format = new PointsByCustomerEmailFormat(customerEmail);
                PointsService pointsService = new PointsService(socket);
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