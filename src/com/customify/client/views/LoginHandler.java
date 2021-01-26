package customify.client.views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class LoginHandler {
    String email;
    String password;
    Socket socket;

    public LoginHandler(Socket socket){
        this.socket = socket;
    }

    public LoginHandler() {

    }

    public void readInputs(String field) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        switch (field) {
            case "email":
                this.email = reader.readLine();
                break;
            case "password":
                this.password = reader.readLine();
        }
    }
}
