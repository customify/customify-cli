package com.customify.client.views;
import com.customify.client.services.BusinessService;

import com.customify.shared.requests_data_formats.GetbusinessFormat;
import com.customify.shared.requests_data_formats.RemoveBusinessFormat;

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
        System.out.println("         3. View business by id");
        System.out.println("         4. Remove business");
        choice = scan.nextInt();

        switch (choice){
            case 1:
                register();
                break;
            case 2:
                viewAll();
                break;
            case 3:
                viewById();
                break;
            case 4:
                deleteBusiness();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    private void viewById()throws IOException, ClassNotFoundException{
        Scanner scan=new Scanner(System.in);
        System.out.print("Enter businessId: \t");
        int businessId = scan.nextInt();
        BusinessService businessService = new BusinessService(socket);
        businessService.getById(businessId);
    }
    private void viewAll()throws IOException, ClassNotFoundException {
        GetbusinessFormat format = new GetbusinessFormat();
        BusinessService businessService = new BusinessService(socket);
        businessService.getbusinesses(format);
    }
    private void deleteBusiness()throws IOException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter businessId: \t");
        int businessId = scan.nextInt();
        Integer format = businessId;
        BusinessService businessService = new BusinessService(socket);
        businessService.deleteBusiness(format);
    }



    private void register() {
        System.out.println("Register business");
    }
}
