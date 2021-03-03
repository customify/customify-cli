/**
 * @description
 *Main view for operating on businesses
 *
 * @author Kellia Umuhire, Anselme Habumugisha
 * @since Wednesday, 3 February 2021
 * */
package com.customify.client.views.billing;

import com.customify.client.services.BillingService;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class FeaturesView {
    private Socket socket;
    private BillingService billingService;
    public FeaturesView(Socket socket){
        this.socket = socket;
        this.billingService = new BillingService(socket);
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
        System.out.println("------------------ SUPER ADMIN > BILLING > FEATURES ---------------------");
        System.out.println("\n         00. Return Home");
        System.out.println("         1. View Featurs");
        System.out.println("         2. Add a Feature");
        System.out.println("         3. Update a Feature");
        System.out.println("         4. Delete a Delete a feature");

        choice = scan.nextInt();

        switch (choice){
            case 1:
                billingService.getFeatures();
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            default:
                System.out.println("Invalid choice");
                loop = false;
        }
    }

    void getFeatures() throws IOException {
        billingService.getFeatures();
    }
}
