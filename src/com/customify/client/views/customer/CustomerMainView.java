package com.customify.client.views.customer;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class CustomerMainView {

    private Socket socket;

    public CustomerMainView(){}


    public CustomerMainView(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void view() throws IOException, ClassNotFoundException {
        boolean customerView = true;

        label:do {
            System.out.println("------------------HOME >> CUSTOMER MANAGEMENT---------------------");
            System.out.println("\n         00. Return Home");
            System.out.println("         1. Add New Customer");
            System.out.println("         2. Read All Customer");
            System.out.println("         3. Search Customer");
            System.out.println("         4. Update Customer");
            System.out.println("         5. Disable Customer");
            Scanner scan = new Scanner(System.in);
            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    NewCustomerView customer =new NewCustomerView(this.socket);
                    customer.view();
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    DisableCustomerView customerView1= new DisableCustomerView(this.socket);
                    customerView1.view();
                    break;
                case "00":
                    customerView = false;
                    break;
                default:
                    System.out.println("INVALID CHOICE");

            }
        } while (customerView);
    }
}
