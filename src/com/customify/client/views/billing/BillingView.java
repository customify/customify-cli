/**
 * @description
 *Main view for operating on billing
 *
 * @author Patrick Niyogitare
 * 
 * */


package com.customify.client.views.billing;

import java.io.IOException;
import java.net.Socket;
import java.util.*;

public class BillingView {
    private Socket socket;

    public BillingView(Socket socket){
        this.socket = socket;
    }
    public Socket getSocket()
    {
        return socket;
    }
    public void setSocket(Socket socket){
        this.socket = socket;
    }

    public void view() throws IOException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        boolean loop = true;
        int choice;
        System.out.println("\t\t\t\t\t------------------ SUPER ADMIN > BILLING ---------------------");
        System.out.println("\n\t\t\t\t\t         00. Return Home");
        System.out.println("\t\t\t\t\t         1. PLANS");
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
