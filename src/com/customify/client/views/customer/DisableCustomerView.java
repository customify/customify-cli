package com.customify.client.views.customer;

import com.customify.client.Colors;
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
        String code;
        String option;

        add:do {
            System.out.println(Colors.ANSI_BLUE);
            System.out.println("\t\t\t\t\t\t\t\t\t\t------------------HOME >> CUSTOMER MANAGEMENT >> DISABLE-CUSTOMER---------------------");
            System.out.println(Colors.ANSI_RESET);
            System.out.print("\t\t\t\t\t\t                            Enter  Customer's code:");
            code = scan.nextLine();
            System.out.print("\t\t\t\t\t\t                            Are you sure you want to di-activate this card?" + Colors.ANSI_RED + " (y/N): \t" + Colors.ANSI_RESET);
            option = scan.nextLine();

            if(option.equalsIgnoreCase("y") || option.equalsIgnoreCase("yes")){
                String json = "{ \"code\" : \""+code+"\", \"key\" : \""+Keys.DISABLE_CUSTOMER+"\" }";
                CustomerService service = new CustomerService(this.socket);
                service.disable(json);
            }
            else if(option.equalsIgnoreCase("n") || option.equalsIgnoreCase("no")){
                System.out.println(Colors.ANSI_CYAN + "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t            QUITED!!!" + Colors.ANSI_RESET);
                return;
            }
        }while(true);
    }
}

