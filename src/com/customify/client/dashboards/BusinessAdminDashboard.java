package com.customify.client.dashboards;

import com.customify.client.Colors;
import com.customify.client.Login;
import com.customify.client.utils.authorization.UserSession;
import com.customify.client.views.customer.CustomerMainView;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.Socket;
import java.util.Scanner;

public class BusinessAdminDashboard {

    private Socket socket;
    private   UserSession  userSession;
    private boolean loggedIn = true;

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

    public BusinessAdminDashboard(Socket socket) throws Exception {
        this.socket = socket;
        this.userSession= new UserSession();
        if(userSession.isLoggedIn())
            this.view();
        else{
            System.out.println("\t\t\tSORRY YOU CAN'T ACCESS THIS ROUTE _ LOG IN FIRST\n\n");
          Login login =new Login(this.socket);
        }

    }


    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }


    public void view()throws Exception{
        Scanner scan = new Scanner(System.in);

        do {

            this.Header();
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t1. EMPLOYEE MANAGEMENT");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t2. CUSTOMER FEEDBACKS");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t3. TODAY'S REPORT");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t4. MY PROFILE");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t5. PROFILE SETTINGS");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t6. LOGOUT !!!");
            System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\tEnter your choice"+Colors.ANSI_YELLOW+" <1-6>"+Colors.ANSI_RESET+": ");
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    CustomerMainView customer = new CustomerMainView(this.getSocket(),true);
                    customer.view();
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
                        if(userSession.unSet())
                            loggedIn=false;
                    break;
                default:
                    System.out.println(Colors.ANSI_RED+"\t\t\t\t\t\t\t\t\t\t\t\t\t\tINVALID CHOICE"+Colors.ANSI_RESET);
            }
        }while(loggedIn);
    }
    public void Header(){
        System.out.println(Colors.ANSI_CYAN);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tBUSINESS ADMIN DASHBOARD");
        System.out.println(Colors.ANSI_RESET);
    }
}
