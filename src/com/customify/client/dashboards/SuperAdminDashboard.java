package com.customify.client.dashboards;

import com.customify.client.utils.authorization.UserSession;
import com.customify.client.views.Business.BusinessView;
import com.customify.client.views.billing.BillingView;

import java.net.Socket;
import java.util.Scanner;

public class SuperAdminDashboard {
    private Socket socket;
    private boolean loggedIn;
    private UserSession userSession;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public SuperAdminDashboard(Socket socket) throws Exception{
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

    public void view()throws Exception{
        Scanner scan = new Scanner(System.in);

        if(isLoggedIn()){
            do {
                System.out.println("---------------------------------------------");
                System.out.println("--------------CUSTOMIFY HOME-----------------\n");
                System.out.println("           1. BUSINESS MANAGEMENT");
                System.out.println("           2. MY PROFILE");
                System.out.println("           3. PROFILE SETTINGS");
                System.out.println("           4. PLANS");
                System.out.println("           5. LOGOUT !!!");
                int choice = scan.nextInt();
                switch (choice) {
                    case 1:
                        BusinessView business = new  BusinessView(this.socket);
                        business.view();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        BillingView billingView = new BillingView(this.socket);
                        billingView.view();
                        break;
                    case 5:
                        if (userSession.unSet())
                            loggedIn = false;
                        break;
                    default:
                        System.out.println("INVALID CHOICE");
                }
            }while(loggedIn);
        }



    }

}
