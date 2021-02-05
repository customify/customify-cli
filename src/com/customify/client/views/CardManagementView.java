package com.customify.client.views;

import com.customify.client.services.CardManagementService;
import com.customify.shared.requests_data_formats.CardDataFormat;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class CardManagementView {
    Socket socket;

    public Socket getSocket() {
        return socket;
    }
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    public CardManagementView(Socket socket){
        this.socket = socket;
    }


    public void init() throws IOException {
        int choice;
        Scanner scanner = new Scanner (System.in);

        System.out.println("--------------------------------------");
        System.out.println("----------CARD MANAGEMENT-----------");
        System.out.println(" 1. Disable the card");
        System.out.println(" 2. Re_enable the card");
        choice = scanner.nextInt();

        switch(choice)
        {
            case 1:
                disableCard();
                break;
            case 2:
               activateCard();
                break;

            default:
                System.out.println("Invalid choice");
        }
    }


    public void disableCard() throws IOException {

        Scanner scanner = new Scanner(System.in);
        long code;
        String option;

        System.out.println("Enter customer code: \t");
        code = Long.parseLong(scanner.nextLine());
        System.out.println("are you sure you want to di-activate this card? (y/N): \t");
        option = scanner.nextLine();

        if(option.equalsIgnoreCase("y") || option.equalsIgnoreCase("yes")){

            CardDataFormat data = new CardDataFormat(code,2);

            CardManagementService cardManagementService = new CardManagementService(this.socket);
            cardManagementService.disableCard(data);
        }
        else{
            return;
        }
    }
    public void activateCard() throws IOException {

        Scanner scanner = new Scanner(System.in);
        long code;
        String option;

        System.out.println("Enter the customer's code");
        code = Long.parseLong(scanner.nextLine());
        System.out.println("Are you sure you want to re_activate this card");
        option=scanner.nextLine();

        if(option.equalsIgnoreCase("y") || option.equalsIgnoreCase("yes")){

        }
        else{
            return;
        }
    }
}
