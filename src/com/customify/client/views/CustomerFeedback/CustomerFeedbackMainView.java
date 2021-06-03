package com.customify.client.views.CustomerFeedback;

/*
* Description
* @author: Niyonzima Stecie
*  This is th class for just showing the options including showing all provided
* feedbacks and deleting them away
* */

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
        CustomerReadFeedbacks readFeedbacks = new CustomerReadFeedbacks(this.socket);
        Scanner scan = new Scanner(System.in);
        int choice;
        System.out.println("------------------Customer feedback operations---------------------");
        System.out.println("         1. Get all feedbacks");
        System.out.println("         2. Delete feedback");
        choice = scan.nextInt();

        switch (choice) {
            case 1:
                readFeedbacks.GetFeedbacks();
                break;
            case 2:
                readFeedbacks.deleteCustomerFeedback();
                break;
            default:
                System.out.println("No such option");
        }
    }
}
