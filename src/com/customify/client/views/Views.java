package customify.client.views;

import java.io.IOException;

public class Views {
    public Views(){};

    public void HomeView() throws IOException {
        System.out.println("---------------------------------------------");
        System.out.println("--------------CUSTOMIFY HOME-----------------");
        System.out.println("\n           1. Sign Up");
        System.out.println("           2. Login In");
        HomeHandler homeHandler = new HomeHandler();
        homeHandler.readData();
    }

    public void SignUpView(){
        System.out.println("------------------SIGNUP---------------------");

        System.out.println("\n         00. Return Home");
        System.out.println("         2. Login");

        System.out.print("\n           Enter Firstname: ");


        System.out.print("\n           Enter Lastname: ");


        System.out.print("\n           Enter Email: ");

    }

    public void LoginView() throws IOException {
            LoginHandler loginHandler = new LoginHandler();
            System.out.println("------------------LOGIN---------------------");
            System.out.println("\n         00. Return Home");
            System.out.println("         1. Signup");

            System.out.print("\n\n           Enter Email: ");
            loginHandler.readInputs("email");

            System.out.print("\n           Enter Password: ");
            loginHandler.readInputs("password");

    }
}
