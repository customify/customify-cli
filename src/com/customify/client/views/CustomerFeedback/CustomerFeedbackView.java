package com.customify.client.views.CustomerFeedback;

/**
 *  Author: Niyonzima Stecie
 * done on: 4 Feb 2021
 * 
 * This class file contains the view which allows the customer to provide his/her
 * feedback about the services he got from the various business.
 * 
 * The data that he/she will provide are the ones to be inserted into the database on the side
 * of the business he/she has written to.
 */


import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class CustomerFeedbackView {
    private Socket socket;

    public CustomerFeedbackView(Socket socket) {
        this.socket = socket;
    }

    public void view() throws IOException, ClassNotFoundException {

        System.out.println("\t\t---------------- Feedback view ---------------\n");

        boolean feed = true;
        Scanner scan = new Scanner(System.in);
        int customer_id, business_id;
        String title, description;

        feedbackLooop: do {
            System.out.print("\n         Enter the customer id: ");
            customer_id = scan.nextInt();

            System.out.print("\tEnter the business id: ");
            business_id = scan.nextInt();

            /*
             * now check if the entered customer id are relevants to the one stored in the
             * database.
             * ---------------------------------
             * -------------------------------
             */
            
            System.out.print("\tEnter the title: ");
            title = scan.next();

            System.out.print("\tEnter the description: ");
            description = scan.next();
            if (description.equals("00"))
                break feedbackLooop;
            feed = false;

//            FeedbackFormat format = new FeedbackFormat(customer_id, business_id, title, description);
//            FeedbackService feedservice = new FeedbackService(this.socket);
//            feedservice.Comment(format);
        } while (feed);
    }
}
