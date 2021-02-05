package com.customify.server.routes;

import java.io.IOException;
import java.net.Socket;
import com.customify.shared.Keys;

public class HandleRoutes {
    private final Keys key;
    Socket socket;
    AuthRoute authRoute;
    BusinessRoute businessRoute;

    public HandleRoutes(Keys key, Socket socket) throws IOException {
        this.socket = socket;
        this.authRoute = new AuthRoute(socket);
        this.key = key;
        this.switchRoutes();
    }

    public void switchRoutes() throws IOException {
        switch (this.key) {
            case LOGIN:
                authRoute.loginRoute();
                break;
            case GET_BUSINESS:
                businessRoute.readBusinessRoute();
                break;
            default:
                System.out.println("Invalid key");
        }
        authRoute.loginError();
    }
}
