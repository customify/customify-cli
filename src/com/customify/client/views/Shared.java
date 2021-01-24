package customify.client.views;

import java.io.IOException;

public class Shared{
//    Signup signup = new Signup();
//    Login login = new Login();
//    Home home = new Home();
      Shared login = new Login();
      Shared signup = new Signup();
      Shared home = new Home();
    public Shared(){};

    public void handleAuthViews(String option) throws IOException {
        switch (option){
            case "1":
                ((Signup) signup).displaySignup();
                break;
            case "2":
                ((Login) login).displayLogin();
            case "00":
                ((Home) home).displayHome();

        }
    }

}
