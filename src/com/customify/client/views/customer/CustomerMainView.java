package com.customify.client.views.customer;

import com.customify.client.Colors;
import com.customify.client.Login;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

public class CustomerMainView {

    private Socket socket;
    private boolean loggedIn =false;

    public CustomerMainView(){}


    public CustomerMainView(Socket socket,boolean loggedIn)throws Exception {
        this.socket = socket;
        Login login;
        setLoggedIn(loggedIn);
//        if(this.loggedIn)
//            this.view();
//         else
//            login = new Login(socket);
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void view() throws IOException, ClassNotFoundException {
        boolean customerView = true;

        if(loggedIn)
        {
            label:do {
                this.Header();
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t1. Add New Customer");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t2. Read All Customer");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t3. Search Customer");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t4. Update Customer");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t5. Disable Customer");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t6. Re-enable Customer");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t00. Back");
                System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\tEnter your choice"+Colors.ANSI_YELLOW+" <1-00>"+Colors.ANSI_RESET+": ");
                Scanner scan = new Scanner(System.in);
                String choice = scan.nextLine();

                switch (choice) {
                    case "1":
                        NewCustomerView customer =new NewCustomerView(this.socket);
                        customer.view();
                        break;
                    case "2":
                        ReadAll customers = new ReadAll(this.socket);
                        customers.view();
                        break;
                    case "3":
                        ReadOne readOne = new ReadOne(this.socket);
                        readOne.view();
                        break;
                    case "4":
                        UpdateCustomerView updatecustomer =new UpdateCustomerView(this.socket);
                        updatecustomer.view();
                        break;
                    case "5":
                        DisableCustomerView customerView1= new DisableCustomerView(this.socket);
                        customerView1.view();
                        break;
                    case "6":
                        ReEnableCustomer activateCustomer = new ReEnableCustomer(this.socket);
                        activateCustomer.init();
                        break;
                    case "00":
                        customerView = false;
                        break;
                    default:
                        System.out.println(Colors.ANSI_RED+"\t\t\t\t\t\t\t\t\t\t\t\t\t\tINVALID CHOICE"+Colors.ANSI_RESET);
                }
            } while (customerView);
        }

    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void Header(){
        System.out.println(Colors.ANSI_GREEN);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\tCUSTOMIFY CUSTOMER MANAGEMENT");
        System.out.println(Colors.ANSI_RESET);
    }
}
