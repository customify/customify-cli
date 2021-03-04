package com.customify.client.views.customer;

import com.customify.client.Keys;
import com.customify.client.data_format.DisableCustomerFormat;
import com.customify.client.services.CustomerService;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class DisableCustomerView {

    private Socket socket;
    public Socket getSocket() { return socket; }

    public void setSocket(Socket socket) { this.socket = socket; }

    public DisableCustomerView(){}

    public DisableCustomerView(Socket socket){this.socket=socket;}

    /**
     * @author Murenzi Confiance Tracy
     * @role
     * this view is to be displayed to the customer who wants to disable a card using customer code
     * */
    public void view() throws IOException, ClassNotFoundException{
        Scanner scan = new Scanner(System.in);
        boolean customerView = true;
        String code;
        String option;

        add:do {
            System.out.println("\t\t\t------------------HOME >> CUSTOMER MANAGEMENT >> DISABLE-CUSTOMER---------------------");
            System.out.println("\n       00. Return ");
            System.out.println("         Enter  Customer's code:");
            code = scan.nextLine();
            System.out.println("         Are you sure you want to di-activate this card? (y/N): \t");
            option = scan.nextLine();

            if(option.equalsIgnoreCase("y") || option.equalsIgnoreCase("yes")){
//                DisableCustomerFormat format = new DisableCustomerFormat(code ,1);
                String json = "{ \"code\" : \""+code+"\", \"key\" : \""+Keys.DISABLE_CUSTOMER+"\" }";
                CustomerService service = new CustomerService(this.socket);
                service.disable(json);
            }
            return;
        }while(true);
    }
}
