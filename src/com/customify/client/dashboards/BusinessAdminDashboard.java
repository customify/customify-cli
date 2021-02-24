package com.customify.client.dashboards;

import com.customify.client.utils.authorization.UserSession;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.Socket;
import java.util.Scanner;

public class BusinessAdminDashboard {

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

    public BusinessAdminDashboard(){}

    public BusinessAdminDashboard(Socket socket) throws JsonProcessingException {
        this.socket = socket;
        this.userSession= new UserSession();
        if(userSession.isLoggedIn())
            this.view();
        else
            System.out.println("\t\t\tSORRY YOU CAN'T ACCESS THIS ROUTE _ LOG IN FIRST");
    }


    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }


    public void view(){
        Scanner scan = new Scanner(System.in);


        do {
            System.out.println("---------------------------------------------");
            System.out.println("--------------CUSTOMIFY HOME-----------------\n");
            System.out.println("--------------BUSINESS ADMIN DASHBOARD-----------------\n");
            System.out.println("           1. EMPLOYEE MANAGEMENT");
            System.out.println("           2. CUSTOMER MANAGEMENT");
            System.out.println("           3. TODAY'S REPORT");
            System.out.println("           4. MY PROFILE");
            System.out.println("           5. PROFILE SETTINGS");
            System.out.println("           6. LOGOUT !!!");
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
//                    loggedIn=false;
                    break;
                    case 5:
//                    loggedIn=false;
                    break;
                case 6:
                    loggedIn=false;
                    break;
                default:
                    System.out.println("INVALID CHOICE");
            }
        }while(loggedIn);
    }

}
