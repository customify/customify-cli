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

package com.customify.client.views.CustomerFeedback;

import com.customify.client.Keys;
import com.customify.client.data_format.CustomerFeedback.CustomerFeedbackDataFormat;
import com.customify.client.services.CustomerFeedbackService;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class CustomerFeedbackView {
    private final Socket socket;

    public CustomerFeedbackView(Socket socket) {
        this.socket = socket;
    }

    public void view() throws IOException, ClassNotFoundException {

        System.out.println("\t\t---------------- Feedback view ---------------\n");

        boolean feed = true;
        Scanner scan = new Scanner(System.in);
        int business_id;
        String title, description,customer_name;

        feedbackLooop: do {

            System.out.print("\tEnter the title: ");
            title = scan.nextLine();

            System.out.print("\tEnter the description: ");
            description = scan.nextLine();
            if (description.equals("00"))
                break feedbackLooop;

            System.out.print("\tEnter your name as customer name: ");
            customer_name = scan.nextLine();

            System.out.print("\tEnter the business id: ");
            business_id = scan.nextInt();
            feed = false;

            CustomerFeedbackDataFormat format = new CustomerFeedbackDataFormat(Keys.FEEDBACK, customer_name, business_id,
                    title, description);
            CustomerFeedbackService feedservice = new CustomerFeedbackService(this.socket);
            feedservice.Feedback(format);
        } while (feed);
    }
}
