package com.customify.client.dashboards;

import com.customify.client.Colors;
import com.customify.client.Login;
import com.customify.client.utils.authorization.UserSession;
import com.customify.client.views.PointCountingView;
import com.customify.client.views.ProductView;
import com.customify.client.views.customer.CustomerMainView;
import com.customify.client.views.CustomerFeedback.CustomerFeedbackView;
import com.customify.client.views.employee.EmployeeMainView;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.Socket;
import java.util.Scanner;

public class BusinessAdminDashboard {

    private Socket socket;
    private UserSession userSession;
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

    public BusinessAdminDashboard() {
    }

    public BusinessAdminDashboard(Socket socket) throws Exception {
        this.socket = socket;
        this.userSession = new UserSession();
        if (userSession.isLoggedIn())
            this.view();
        else {
            System.out.println("\t\t\tSORRY YOU CAN'T ACCESS THIS ROUTE _ LOG IN FIRST\n\n");
            Login login = new Login(this.socket);
        }

    }


    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }


    public void view() throws Exception {
        Scanner scan = new Scanner(System.in);

        do {

            this.Header();
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t1. PRODUCT MANAGEMENT");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t2. EMPLOYEE MANAGEMENT");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t3. CUSTOMER MANAGEMENT");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t4. WINNERS");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t5. TODAY'S REPORT");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t6. MY PROFILE");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t7. PROVIDE FEEDBACK");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t8. PROFILE SETTINGS");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t9. LOGOUT !!!");
            System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\tEnter your choice" + Colors.ANSI_YELLOW + " <1-8>" + Colors.ANSI_RESET + ": ");
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    ProductView productView = new ProductView(this.getSocket());
                    productView.init();
                    break;
                case 2:
                    EmployeeMainView employee = new EmployeeMainView(this.getSocket(),true);
                    employee.view();
                    break;
                case 3:
                    CustomerMainView customer = new CustomerMainView(this.getSocket(), true);
                    customer.view();
                    break;
                case 4:
                    PointCountingView pointCountingView = new PointCountingView(this.socket);
                    pointCountingView.view();
                case 5:
                    break;
                case 6:
//                    loggedIn=false;
                    break;
                case 7:
                    CustomerFeedbackView feedbackView = new CustomerFeedbackView(this.socket);
                    feedbackView.view();
                    break;
                case 9:
                    if (userSession.unSet())
                        loggedIn = false;
                    break;
                default:
                    System.out.println(Colors.ANSI_RED + "\t\t\t\t\t\t\t\t\t\t\t\t\t\tINVALID CHOICE" + Colors.ANSI_RESET);
            }
        } while (loggedIn);
    }

    public void Header() {
        System.out.println(Colors.ANSI_CYAN);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tBUSINESS ADMIN DASHBOARD");
        System.out.println(Colors.ANSI_RESET);
    }
}
