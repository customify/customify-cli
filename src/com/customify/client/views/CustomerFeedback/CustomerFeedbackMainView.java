package com.customify.client.views.CustomerFeedback;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

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
        Scanner scan = new Scanner(System.in);
        int choice;
        System.out.println("------------------Customer feedback operations---------------------");
        System.out.println("\n         00. Get back home");
        System.out.println("         1. Provide feedback");
        System.out.println("         2. Get all feedbacks");
        choice = scan.nextInt();

        switch (choice) {
            case 1:
                CustomerFeedbackView cfeedback = new CustomerFeedbackView(this.socket);
                cfeedback.view();
                break;
            case 2:
                CustomerReadFeedbacks readFeedbacks = new CustomerReadFeedbacks(this.socket);
                readFeedbacks.GetFeedbacks();
                break;
            default:
                System.out.println("No such option");
        }
    }
}
