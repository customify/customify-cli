package customify.client.services;

import customify.client.Common;
import customify.shared.Request;
import customify.shared.data_format.LoginFormat;
import customify.shared.data_format.SignUpFormat;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Auth {

    private Socket socket;
    private String data;

    public Auth(Socket socket){
        this.socket = socket;
    }
    public Socket getSocket()
    {
        return socket;
    }
    public void setSocket(Socket socket){
        this.socket = socket;

    }

    public void login(LoginFormat format) throws IOException {
        String key ="USER_LOGIN";
        Request request = new Request(key,format);
        Common common = new Common(request,this.socket);
    }
    public void signUp(SignUpFormat format) throws IOException {
        String key ="USER_SIGNUP";
        Request request = new Request(key,format);
        Common common = new Common(request,this.socket);

    }

}
