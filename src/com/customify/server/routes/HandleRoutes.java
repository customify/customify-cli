package customify.server.routes;

import java.io.IOException;
import java.net.Socket;

public class HandleRoutes {
    private String key;
    Socket socket;
    AuthRoute authRoute;

    public HandleRoutes(String key, Socket socket) throws IOException {
        this.socket = socket;
        this.authRoute = new AuthRoute(socket);
        this.key = key;
        this.swithcRoutes();
    }

    public void swithcRoutes() throws IOException {
        switch (this.key) {
            case "login":
                authRoute.loginRoute();
            default:
                authRoute.loginError();
        }
    }
}
