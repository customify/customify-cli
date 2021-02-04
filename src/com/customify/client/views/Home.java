/*
  Class to display the Home view
*/
package com.customify.client.views;

import com.customify.client.views.Business.BusinessRegisterView;

import java.io.IOException;
import java.net.Socket;
import  java.util.*;

public class Home {

    private Socket socket;

    /**
     * @param socket for using to work with backend
     */
    public Home(Socket socket){
        this.socket = socket;
    }

    /**
     *
     * @return socket the current socket
     */
    public Socket getSocket()
    {
        return socket;
    }


    /**
     *
     * @param socket
     */
    public void setSocket(Socket socket){
        this.socket = socket;
    }


    public void view() throws IOException, ClassNotFoundException, InterruptedException {

        int choice;
        Scanner scan = new Scanner(System.in);
        LoginView loginView =new LoginView(this.socket);
        SignupView signupView =new SignupView(this.socket);
        var businessRegisterView = new BusinessRegisterView(this.socket);

        System.out.println("---------------------------------------------");
        System.out.println("            CUSTOMIFY HOME                    ");
        System.out.println("\n           1. Sign Up");
        System.out.println("           2. Login In");

        System.out.println("\n");

        System.out.println("Enter your choice here: ");
        System.out.println("           3. Register a business ");
        choice = scan.nextInt();

        switch(choice)
        {
            case 1:
                clearScreen();
                signupView.view();
                break;
            case 2:
                clearScreen();
                loginView.view();
                break;
            case 3:
                businessRegisterView.view();
            default:
                clearScreen();
                System.out.println("Invalid choice");
        }

    }

    /**
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public static void clearScreen() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}
