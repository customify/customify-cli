package com.customify.server.routes;

import java.io.IOException;
import java.net.Socket;
import com.customify.shared.Keys;

public class HandleRoutes {
    private final Keys key;
    Socket socket;
    AuthRoute authRoute;
    PointsRoutes pointsRoutes;

    public HandleRoutes(Keys key, Socket socket) throws IOException {
        this.socket = socket;
        this.authRoute = new AuthRoute(socket);
        this.pointsRoutes = new PointsRoutes(socket);
        this.key = key;
        this.switchRoutes();
    }

    public void switchRoutes() throws IOException {
//        if (this.key == Keys.LOGIN) {
//            authRoute.loginRoute();
//        }

        switch (this.key){
            case LOGIN:
                authRoute.loginRoute();
                break;
            case POINTS_BY_CUSTOMER_EMAIL:
                 pointsRoutes.getPointsByCustomer();
                 break;
        }
//        authRoute.loginError();
    }
}
