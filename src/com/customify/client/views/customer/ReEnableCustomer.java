package com.customify.client.views.customer;

import com.customify.client.data_format.DeActivateCustomer;
import com.customify.client.services.CustomerService;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Murenzi Confiance Tracy
 * @role
 * this view is to be displayed to the customer who wants to re-enable a card using customer code
 * */

public class ReEnableCustomer {
    public ReEnableCustomer(){}

    private Socket socket;
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ReEnableCustomer(Socket socket){this.socket=socket;}
    Scanner scan = new Scanner(System.in);
    String code;
    String option;

    public void init() throws IOException, ClassNotFoundException {
        System.out.println("\t\t\t------------------HOME >> CUSTOMER MANAGEMENT >> RE-ENABLE CUSTOMER---------------------");
        System.out.println("         Enter  Customer's code:");
        code = scan.nextLine();
        System.out.println("         Are you sure you want to activate this card? (y/N): \t");
        option = scan.nextLine();
        CustomerService service = new CustomerService(socket);

        if(option.equalsIgnoreCase("y") || option.equalsIgnoreCase("yes")){
            DeActivateCustomer reEnabled= new DeActivateCustomer(code);
            CustomerService deactivateService = new CustomerService(this.socket);
            deactivateService.reEnableCard(code);
        }
        else{
            return;
        }
    }
}
