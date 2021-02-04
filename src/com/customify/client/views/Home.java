/*
  Class to display the Home view
*/
package com.customify.client.views;
import java.net.Socket;
import  java.util.*;

public class Home {

    private Socket socket;

    public Home(Socket socket){
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

        int choice;
        Scanner scan = new Scanner(System.in);
        LoginView loginView =new LoginView(this.socket);
        SignupView signupView =new SignupView(this.socket);
        ProductView productView = new ProductView(this.socket);

        System.out.println("---------------------------------------------");
        System.out.println("--------------CUSTOMIFY HOME-----------------\n");
        System.out.println("           1. SIGN UP");
        System.out.println("           2. LOGIN");
        System.out.println("           3. PRODUCT MANAGEMENT");
        choice = scan.nextInt();

        switch(choice)
        {
            case 1:
                signupView.view();
                break;
            case 2:
                loginView.view();
                break;
            case 3:
                productView.init();
                break;
            default:
                System.out.println("Invalid choice");
        }

    }
}
