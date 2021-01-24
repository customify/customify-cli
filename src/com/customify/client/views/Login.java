package customify.client.views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Login extends Shared {
//    public Login(){};
    String email;
    String password;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//    Shared shared = new Shared();
    public void displayLogin() throws IOException {
        System.out.println("------------------LOGIN---------------------");
        System.out.println("\n         00. Return Home");
        System.out.println("         1. Signup");

        System.out.print("\n\n           Enter Email: ");
        email = reader.readLine();
        handleAuthViews(email);

        System.out.print("\n           Enter Password: ");
        password = reader.readLine();
        handleAuthViews(password);

    }


}
