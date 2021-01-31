package com.customify.server.routes;

import java.io.IOException;
import java.net.Socket;
import com.customify.shared.Keys;

public class HandleRoutes {
    private final Keys key;
    Socket socket;
    AuthRoute authRoute;

    public HandleRoutes(Keys key, Socket socket) throws IOException {
        this.socket = socket;
        this.authRoute = new AuthRoute(socket);
        this.key = key;
        this.switchRoutes();
    }

    public void switchRoutes() throws IOException {
        if (this.key == Keys.LOGIN) {
            authRoute.loginRoute();
        }
        authRoute.loginError();
    }
}
