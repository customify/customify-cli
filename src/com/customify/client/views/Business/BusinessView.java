package com.customify.client.views.Business;


import java.io.IOException;
import java.net.Socket;
import java.util.*;

public class BusinessView {
    private Socket socket;

    public BusinessView(Socket socket){
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
        Scanner scan = new Scanner(System.in);
        boolean loop = true;
        int choice;
        System.out.println("------------------BUSINESS---------------------");
        System.out.println("\n         00. Return Home");
        System.out.println("         1. Register business");
        System.out.println("         2. View businesses");
        choice = scan.nextInt();

        switch (choice){
            case 1:
                register();
                break;
            case 2:
                BusinessReadView businessReadView = new BusinessReadView(this.socket);
                businessReadView.viewAll();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    private void register() {
        System.out.println("Register business");
    }
}
