package com.customify.server.routes;

import com.customify.server.controllers.AuthController;

import java.io.IOException;
import java.net.Socket;

public class AuthRoute {
    Socket socket;
    AuthController authController;

    public AuthRoute(Socket socket) {
        this.socket = socket;
    }

    public AuthRoute() {

    }

    public void  loginRoute() throws IOException {
  //      authController.login(this.socket);
    }

    public void loginError() throws IOException {
     //   authController.loginError(this.socket);
    }
}
