package customify.client.views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Signup extends Shared{
    public Signup(){};
//    Login login = new Login();
//    Home home = new Home();
    String firstname;
    String lastname;
    String email;
    String password;


    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//    Shared shared = new Shared();


    public void displaySignup() throws IOException {
        System.out.println("------------------SIGNUP---------------------");

        System.out.println("\n         00. Return Home");
        System.out.println("         2. Login");

        System.out.print("\n           Enter Firstname: ");
        firstname = reader.readLine();
        handleAuthViews(firstname);

        System.out.print("\n           Enter Lastname: ");
        lastname = reader.readLine();
        handleAuthViews(lastname);


        System.out.print("\n           Enter Email: ");
        email = reader.readLine();
        handleAuthViews(email);


        System.out.print("\n           Enter Password: ");
        password = reader.readLine();
        handleAuthViews(password);

    }

    public void handleAuthViews(String option) throws IOException {
        switch (option){

            case "2":
                handleAuthViews("2");
            case "00":
                handleAuthViews("00");

        }
    }

//        ToDo send data to backend for account creating

}
