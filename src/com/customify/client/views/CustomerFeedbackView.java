package com.customify.client.views;

/**
 * This class file contains the view which allows the customer to provide his/her
 * feedback about the services he got from the various business
 */

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

        boolean feed = true;
        Scanner scan = new Scanner(System.in);
        int cust_id, bus_id;
        String title, descript;        

        feedbackLooop: do {
            System.out.print("\n         Enter the customer id: ");
            cust_id = scan.nextInt();

            System.out.print("\tEnter the business id: ");
            bus_id = scan.nextInt();

            System.out.print("\tEnter the description: ");
            descript = scan.nextLine();
            if (descript.equals("00"))
                break feedbackLooop;
                
            System.out.print("\tEnter the title: ");
            title = scan.nextLine();
            if (title.equals("00"))
                break feedbackLooop;
                feed = false;        
            FeedbackFormat format = new FeedbackFormat(cust_id, bus_id, title, descript);
            FeedbackService feedservice = new FeedbackService(socket);
            feedservice.Comment(format);
        } while (feed);
    }
}
