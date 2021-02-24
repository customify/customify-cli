package com.customify.client;

import com.customify.client.dashboards.BusinessAdminDashboard;
import com.customify.client.dashboards.EmployeeDashboard;
import com.customify.client.dashboards.SuperAdminDashboard;
import com.customify.client.data_format.AuthenticationDataFormat;
import com.customify.client.services.AuthService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Login {
    public Login() {
    }

    private Socket socket;

    public Login(Socket socket) {
        this.socket = socket;
    }

    public void view() throws IOException, ClassNotFoundException {

        authorize:
        while (true) {
            System.out.println("\n\n\n\t\t\t\t\tWELCOME ON  CUSTOMIFY  SYSTEM\n\n");

            Scanner scan = new Scanner(System.in);
            String email, password;
            System.out.println("\t\t\t00.Exit");
            System.out.println("\t\t------------------------------------------------------------------------------");
            System.out.println("\t\t\t\t\t\t\tLOGIN\n");
            System.out.println("\t\t------------------------------------------------------------------------------");
            System.out.print("\t\t\tEmail:.... ");
            email = scan.nextLine();

            if (email.equals("00"))
                break authorize;

            System.out.print("\n\t\t\tPassword:.... ");
            password = scan.nextLine();

            System.out.println("\t\t------------------------------------------------------------------------------");

            if (password.equals("00"))
                break authorize;


            AuthenticationDataFormat format = new AuthenticationDataFormat(email, password);
            AuthService authService = new AuthService(this.socket, format);
//            authService.authenticateAdmin();

            if (authService.authenticate()) {
//                switch (authService.getLoggedInUser()) {
//                    case "BUSINESS_ADMIN":
//                        BusinessAdminDashboard bussDashboard = new BusinessAdminDashboard(this.socket);
//                        break;
//                    case "EMPLOYEE":
//                        EmployeeDashboard empDashboard = new EmployeeDashboard(this.socket);
//                        break;
//                    case "SUPER_ADMIN":
//                        SuperAdminDashboard admDashboard = new SuperAdminDashboard(this.socket);
//                        break;
//                    default:
//                        System.out.println("\t\t\tINVALID CHOICE");
//                }
            } else {
                System.out.println("\t\t\t\t\t SORRY CHECK YOUR PASSWORD OR EMAIL");
            }
        }
    }
}
