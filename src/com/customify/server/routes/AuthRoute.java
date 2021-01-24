package customify.server.routes;

import customify.server.controllers.Auth;

import java.io.IOException;
import java.net.Socket;

public class AuthRoute {
    Socket socket;
    Auth auth = new Auth();

    public AuthRoute(Socket socket) {
        this.socket = socket;
    }

    public AuthRoute() {

    }

    public void  loginRoute() throws IOException {
        auth.login(this.socket);
    }

    public void loginError() throws IOException {
        auth.loginError(this.socket);
    }
}
