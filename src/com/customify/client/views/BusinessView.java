package com.customify.client.views;
import com.customify.client.services.BusinessService;

import com.customify.shared.requests_data_formats.GetbusinessFormat;
//import com.customify.shared.requests_data_formats.RemoveBusinessFormat;

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
                viewAll();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }


    private void viewAll()throws IOException, ClassNotFoundException {
        GetbusinessFormat format = new GetbusinessFormat();
        BusinessService businessService = new BusinessService(socket);
        businessService.getbusinesses(format);
    }

    private void register() {
        System.out.println("Register business");
    }
}
