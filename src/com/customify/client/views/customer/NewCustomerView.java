package com.customify.client.views.customer;

import com.customify.client.data_format.CreateCustomerFormat;
import com.customify.client.services.CustomerService;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class NewCustomerView {

    private Socket socket;

    public NewCustomerView(){ }

    public NewCustomerView(Socket socket) {
        this.socket = socket;
    }

    public void view() throws IOException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);

        add:do{
            System.out.println("\t\t\t------------------HOME >> CUSTOMER MANAGEMENT >> NEW-CUSTOMER---------------------");
            System.out.println("\n       00. Return ");
            System.out.println("        Enter  Customer First Name");
            String customerFirstName = scan.nextLine();

            if (customerFirstName.equals("00"))
                break add;


            System.out.println("        Enter  Customer Last Name");
            String customerLastName = scan.nextLine();
            if (customerLastName.equals("00"))
                break add;


            System.out.println("        Enter  Customer Email");
            String customerEmail = scan.nextLine();
            if (customerEmail.equals("00"))
                break add;


            CreateCustomerFormat format = new CreateCustomerFormat(customerEmail, customerLastName, customerFirstName);

            CustomerService customerService = new CustomerService(this.socket);
            customerService.create(format);
        }while(true);
    }
}