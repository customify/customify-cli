package com.customify.client.views.reporting;

import com.customify.client.views.billing.FeaturesView;
import com.customify.client.views.billing.PlansView;

import java.net.Socket;
import java.util.Scanner;

public class ReportingView {
    private Socket socket;

    public ReportingView(Socket socket){
        this.socket = socket;
    }
    public Socket getSocket()
    {
        return socket;
    }
    public void setSocket(Socket socket){
        this.socket = socket;
    }

    public void view() throws Exception {
        Scanner scan = new Scanner(System.in);
        boolean loop = true;
        int choice;
        System.out.println("\t\t\t\t\t------------------ BISSINESS ADMIN > REPORTING ---------------------");
        System.out.println("\n\t\t\t\t\t         00. Return Home");
        System.out.println("\t\t\t\t\t         1. ");
        System.out.println("\t\t\t\t\t         2. FEATURES");
        choice = scan.nextInt();

        switch (choice){
            case 1:
                PlansView plansView = new PlansView(this.socket);
                plansView.view();
                break;
            case 2:
                FeaturesView featuresView = new FeaturesView(this.socket);
                featuresView.view();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
}
