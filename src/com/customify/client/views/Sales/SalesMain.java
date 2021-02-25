package com.customify.client.views.Sales;


/*
* By Makuza Mugabo Verite
* On Feb 25/02/2021
* */

import java.net.Socket;
import java.util.Scanner;

public class SalesMain {
    private Socket socket;
    private boolean isLoggedIn = false;

    public SalesMain(Socket socket, boolean isLoggedIn) {
       setLoggedIn(isLoggedIn);
       setSocket(socket);

       if(this.isLoggedIn())
           this.main();
    }

    public void main(){

        Scanner scanner = new Scanner(System.in);
        boolean showView = true;

      do {
                System.out.println("|---------------------------------------------------|");
                System.out.println("|              SALES MANAGEMENT                     |");
                System.out.println("|___________________________________________________|");
                System.out.println("| 1. Sell Product                                   |");
                System.out.println("| 2. List all sales                                 |");
                System.out.println("| 3. View sales Made by a customer                  |");
                System.out.println("| 4. Reports about sales                            |");
                System.out.println("| 00. Back                                          |");
                System.out.println("|---------------------------------------------------|");

                System.out.println("Enter your choice here -> ");
                int choice = scanner.nextInt();

                switch (choice){
                    case 1:
                        this.SaleProductView();
                        break;
                    case 2:
                        break;
                    case 4:
                        break;
                    case 00:
                        showView = false;
                        break;
                    default:
                        System.out.println("INVALID CHOICE!");
                }
            }while (showView);
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }


    public void SaleProductView(){

        Scanner scanner = new Scanner(System.in);
        String productId;

        System.out.println("|---------------------------------------------------|");
        System.out.println("|              SALES MANAGEMENT                     |");
        System.out.println("|___________________________________________________|");

        System.out.println("Enter Product id");
        productId = scanner.nextLine();

        System.out.println("Product id "+productId);
    }

}
