package com.customify.client.dashboards;

import com.customify.client.Colors;
import com.customify.client.utils.authorization.UserSession;
import com.customify.client.views.customer.CustomerMainView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.customify.client.views.Coupon;
import com.customify.client.views.Sales;

import java.net.Socket;
import java.util.Scanner;

public class EmployeeDashboard {

    private Socket socket;
    private   UserSession  userSession;
    private boolean loggedIn = false;

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public EmployeeDashboard(){}

    public EmployeeDashboard(Socket socket) throws Exception {
        this.socket = socket;
        this.userSession= new UserSession();
        if(userSession.isLoggedIn()) {
            this.setLoggedIn(true);
            this.view();
        }else
            System.out.println("\t\t\tSORRY YOU CAN'T ACCESS THIS ROUTE _ LOG IN FIRST");
    }


    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }


    public void view() throws Exception {
        Scanner scan = new Scanner(System.in);
        if(isLoggedIn()){
        do {
            System.out.println(Colors.ANSI_CYAN);
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tCUSTOMIFY HOME");
            System.out.println(Colors.ANSI_RESET);
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t1. Customer Management");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t2. Sales Management");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t3. Coupon Management");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t4. My Profile");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t5. Profile Settings");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t6. Logout\n");
            System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\tEnter your choice"+Colors.ANSI_YELLOW+" <1-6>"+Colors.ANSI_RESET+": ");
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    CustomerMainView customer = new CustomerMainView(this.socket,this.isLoggedIn());
                    break;
                case 2:
                    Sales sales = new Sales(this.socket,this.isLoggedIn());
                    break;
                case 3:
                    Coupon coupon = new Coupon(this.socket,this.isLoggedIn());
                    break;
                case 4:
//                    loggedIn=false;
                    break;
                case 5:
                    break;
                case 6:
                    if(userSession.unSet())
                        loggedIn=false;
                    break;
                default:
                    System.out.println(Colors.ANSI_RED+"\t\t\t\t\t\t\t\t\t\t\t\t\t\tINVALID CHOICE"+Colors.ANSI_RESET);
            }
        }while(loggedIn);
        }
    }
}
