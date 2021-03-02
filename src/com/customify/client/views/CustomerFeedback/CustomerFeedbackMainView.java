package com.customify.client.views.CustomerFeedback;

import com.customify.client.views.CustomerFeedback.CustomerReadFeedbacks;
import java.io.IOException;
import java.net.Socket;
import java.util.*;

public class CustomerFeedbackMainView {
    private Socket socket;

    public CustomerFeedbackMainView(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void view() throws IOException, ClassNotFoundException {
        CustomerReadFeedbacks mainView = new CustomerReadFeedbacks(this.socket);
        Scanner scan = new Scanner(System.in);
        boolean loop = true;
        int choice;
        System.out.println("------------------Customer feedback operations---------------------");
        System.out.println("\n         00. Get back home");
        System.out.println("         1. Get all feedbacks");
        System.out.println("         2. Delete feedback By id");
        choice = scan.nextInt();

        switch (choice) {
            case 1:
                mainView.GetFeedbacks();
                break;
            case 2:
                System.out.println("On choice 2");
                break;

            default:
                System.out.println("No such option");
        }
    }
}
