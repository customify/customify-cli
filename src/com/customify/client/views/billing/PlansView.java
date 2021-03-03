/**
 * @description
 *Main view for operating on businesses
 *
 * @author Kellia Umuhire, Anselme Habumugisha
 * @since Wednesday, 3 February 2021
 * */
package com.customify.client.views.billing;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class PlansView {
    private Socket socket;

    public PlansView(Socket socket){
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
        System.out.println("------------------ SUPER ADMIN > BILLING > PLANS ---------------------");
        System.out.println("\n         00. Return Home");
        System.out.println("         1. View plans");
        System.out.println("         2. Add a plan");
        System.out.println("         3. Update a plan");
        System.out.println("         4. Delete a plan");

        choice = scan.nextInt();

        switch (choice){
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 0:

                loop = false;
            default:
                System.out.println("Invalid choice");
                loop = false;
        }
    }
}

