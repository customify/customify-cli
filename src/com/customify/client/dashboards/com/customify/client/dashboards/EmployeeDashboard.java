package com.customify.client.dashboards;

import com.customify.client.utils.authorization.UserSession;
import com.customify.client.views.CouponingMain.CouponMainView;
import com.customify.client.views.Sales.SalesMain;
import com.customify.client.views.customer.CustomerMainView;
import com.fasterxml.jackson.core.JsonProcessingException;

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
            System.out.println("---------------------------------------------");
            System.out.println("--------------CUSTOMIFY HOME-----------------\n");
            System.out.println("           1. CUSTOMER MANAGEMENT");
            System.out.println("           2. SALES MANAGEMENT");
            System.out.println("           3. COUPON MANAGEMENT");
            System.out.println("           4. MY PROFILE");
            System.out.println("           5. PROFILE SETTINGS");
            System.out.println("           6. LOGOUT !!!");
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    CustomerMainView customer = new CustomerMainView(this.socket,this.isLoggedIn());
                    break;
                case 2:
                    SalesMain salesMain = new SalesMain(this.socket,this.isLoggedIn());
                    break;
                case 3:
                    CouponMainView couponMainView = new CouponMainView(this.socket,this.isLoggedIn());
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
                    System.out.println("INVALID CHOICE");
            }
        }while(loggedIn);
        }
    }
}
