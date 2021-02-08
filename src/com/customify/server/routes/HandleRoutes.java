package com.customify.server.routes;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

import com.customify.shared.Keys;

public class HandleRoutes {
    private final Keys key;
    Socket socket;
    AuthRoute authRoute;
    PointsRoutes pointsRoutes;
    ProductRoute productRoute;

    public HandleRoutes(Keys key, Socket socket) throws IOException, SQLException {
        this.socket = socket;
        this.authRoute = new AuthRoute(socket);
        this.pointsRoutes = new PointsRoutes(socket);
        this.productRoute = new ProductRoute(socket);
        this.key = key;
        this.switchRoutes();
    }

    public void switchRoutes() throws IOException, SQLException {
        switch (this.key){
            case LOGIN:
                authRoute.loginRoute();
                break;
            case POINTS_BY_CUSTOMER_EMAIL:
                 pointsRoutes.getPointsByCustomer();
                 break;
            case GET_PRODUT_BY_ID:
                productRoute.registerProduct();
                break;
            case DELETE_PRODUCT:
                productRoute.deleteProduct();
                break;
        }
    }
}
