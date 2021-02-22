package com.customify.client.views.customer;

/**
 * @author Nshimiye Emmy
 * @role
 * this is the class to implement the update customer view to show interface of update customer
 * */

import com.customify.client.services.CustomerService;
import com.customify.client.data_format.UpdateCustomerFormat;


import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class UpdateCustomerView {
    private Socket socket;

    public UpdateCustomerView(){ }

    public UpdateCustomerView(Socket socket) {
        this.socket = socket;
    }

    public void view() throws IOException, ClassNotFoundException {
        boolean customerView = true;
        Scanner scan = new Scanner(System.in);
        update:do{
            System.out.println("\t\t\t------------------HOME >> CUSTOMER MANAGEMENT >> UPDATE-CUSTOMER---------------------");
            System.out.println("\n       00. Return ");
            System.out.println("        Enter  Customer Code:");
            String customer_code = scan.nextLine();
            String firstname = "";
            String lastname = "";
            String email = "";

            if (customer_code.equals("00"))
                break update;
            if(customer_code.equals("1234")){
                System.out.println("........Customer " +  customer_code+ " Details......");
                System.out.println("1.First name is: Nshimiye");
                System.out.println("2.Last name is: Emmy");
                System.out.println("3.Email is: nshimiyee311@gmail.com \n");

                String choice = "";
                while (!choice.equals("00")) {
                    System.out.println("Enter your choice to Update or 00 to go back: ");
                    choice = scan.nextLine();

                    switch (choice) {
                        case "1":
                            System.out.println("Enter your first name");
                            firstname = scan.nextLine();
                            System.out.println("You have successfully Updated your first name to: " + firstname  );
                            break;
                        case "2":
                            System.out.println("Enter your last name");
                            lastname = scan.nextLine();
                            System.out.println("You have successfully Updated your last name to: " + lastname  );
                            break;
                        case "3":
                            System.out.println("Enter your email address");
                            email = scan.nextLine();
                            System.out.println("You have successfully Updated your email to: " + email);
                            break;
                        case "00":
                            customerView = false;
                            break;

                        default:
                            System.out.println("Invalid choice to update-Try again");
                            break;
                    }
                }

            }

            UpdateCustomerFormat format = new UpdateCustomerFormat(customer_code,email,lastname,firstname);
            CustomerService customerService = new CustomerService(this.socket);
            customerService.update(format);
        }while(true);
    }


}
