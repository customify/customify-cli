package customify.client.views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Home  extends Shared{
    public Home(){};
//    Signup signup = new Signup();
//    Login login = new Login();
//    Shared shared = new Shared();

    public void displayHome() throws IOException {
        System.out.println("---------------------------------------------");
        System.out.println("--------------CUSTOMIFY HOME-----------------");
        System.out.println("\n           1. Sign Up");
        System.out.println("           2. Login In");

        this.readHomeData();

    }

    public void readHomeData() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int homeInput = reader.read();
        this.handInput(homeInput);
    }

    public void handInput(int input) throws IOException {
        switch (input){
            case 1: handleAuthViews("1");
            break;

            case 2: handleAuthViews("2");
            break;

            default:
                System.out.println("             Invalid option");
               handleAuthViews("00");
        }
    }
}
