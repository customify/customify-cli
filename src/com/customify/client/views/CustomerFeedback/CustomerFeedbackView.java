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

import com.customify.client.services.FeedbackService;
import com.customify.shared.requests_data_formats.FeedbackFormat;
import com.customify.server.Db.Db;
import java.sql.ResultSet;

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

        Db.getConnection();
        Db.getStatement();
        
        boolean feed = true;
        Scanner scan = new Scanner(System.in);
        int cust_id, bus_id;
        String title, descript;

        feedbackLooop: do {
            System.out.print("\n         Enter the customer id: ");
            cust_id = scan.nextInt();

            System.out.print("\tEnter the business id: ");
            bus_id = scan.nextInt();

            // now check if the entered customer id are relevants to the one stored in the database.

            System.out.print("\tEnter the title: ");
            title = scan.next();            
            
            System.out.print("\tEnter the description: ");
            descript = scan.next();
            if (descript.equals("00"))
                break feedbackLooop;
            feed = false;

            FeedbackFormat format = new FeedbackFormat(cust_id, bus_id, title, descript);
            FeedbackService feedservice = new FeedbackService(socket);
            feedservice.Comment(format);
        } while (feed);
    }
}
