package com.customify.client;
import com.customify.client.dashboards.BusinessAdminDashboard;
import com.customify.client.dashboards.EmployeeDashboard;
import com.customify.client.dashboards.SuperAdminDashboard;
import com.customify.client.data_format.AuthenticationDataFormat;
import com.customify.client.services.AuthService;
import com.customify.client.utils.authorization.UserSession;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
public class Login {
    private Socket socket;
    public Login()  { }
    public Login(Socket socket) throws Exception{
        this.socket = socket;

        UserSession userSession = new UserSession();
        if(userSession.isLoggedIn())
        {
            String json = userSession.getUserJsonObject();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);
            route(jsonNode.get("appUser").asText());
        }else{
            openLogin=true;
            this.view();
        }
    }
    private boolean openLogin = false;
    public void view() throws Exception{

        authorize:do{

            System.out.println(Colors.ANSI_PURPLE);
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWELCOME ON  CUSTOMIFY  SYSTEM");
            System.out.println(Colors.ANSI_RESET);

            Scanner scan = new Scanner(System.in);
            String email, password;
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\tLOGIN\n");
            System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\tEmail: ");
            email = scan.nextLine();
            if (email.equals("00"))
                break authorize;

            System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\tPassword: ");
            password = scan.nextLine();


            if (password.equals("00"))
                break authorize;

            AuthenticationDataFormat format = new AuthenticationDataFormat(email, password);
            AuthService authService = new AuthService(this.socket, format);

            if (authService.authenticate()) {
                route(authService.getLoggedInUser());
            } else {
                System.out.println(Colors.ANSI_RED+"\t\t\t\t\t\t\t\t\t\t\t\t\t\tSORRY CHECK YOUR PASSWORD OR EMAIL"+Colors.ANSI_RESET);
            }
        }
        while(openLogin);
    }
    public void route(String appUser) throws Exception{
        switch (appUser) {
            case "BUSINESS_ADMIN":


                BusinessAdminDashboard bussDashboard = new BusinessAdminDashboard(this.socket);

                break;
            case "EMPLOYEE":
                EmployeeDashboard empDashboard = new EmployeeDashboard(this.socket);

                break;
            case "SUPER_ADMIN":
                SuperAdminDashboard admDashboard = new SuperAdminDashboard(this.socket);
                break;
            default:
                System.out.println(Colors.ANSI_RED+"\t\t\t\t\t\t\t\t\t\t\t\t\t\tINVALID CHOICE"+Colors.ANSI_RESET);
        }
    }
}