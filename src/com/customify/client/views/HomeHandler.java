package customify.client.views;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class HomeHandler {
    String input;

    public HomeHandler(){

    };

    public void handleInput() throws IOException {
        Views views = new Views();
        switch(this.input){
            case "1":
                views.SignUpView();
                break;
            case "2":
                 views.LoginView();
                 break;
            default:
                System.out.println("Invalid option");

        }
    }

    void readData() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        this.input = reader.readLine();
        this.handleInput();
    }

}
