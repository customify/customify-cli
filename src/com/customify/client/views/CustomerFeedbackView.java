package com.customify.client.views;

/**
 * This class file contains the view which allows the customer to provide his/her
 * feedback about the services he got from the various business
 */

import com.customify.client.services.AuthService;
import com.customify.client.services.FeedbackService;
import com.customify.shared.requests_data_formats.FeedbackFormat;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class CustomerFeedbackView {
    private Socket socket;

    public CustomerFeedbackView(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void view() throws IOException, ClassNotFoundException {

        System.out.println("\t\t---------------- Feedback view ---------------\n");

        int cust_id, bus_id;
        String title, descript;

        Scanner scan = new Scanner(System.in);

        System.out.println("\tEnter the customer id: ");
        cust_id = scan.nextInt();

        System.out.println("\tEnter the business id: ");
        bus_id = scan.nextInt();

        System.out.println("\tEnter the title: ");
        title = scan.nextLine();

        System.out.println("\tEnter the description: ");
        descript = scan.nextLine();

        FeedbackFormat format = new FeedbackFormat(cust_id, bus_id, title, descript);
        FeedbackService feedservice = new FeedbackService(socket);
        feedservice.Comment(format);

    }
}
