package com.customify.client.views.CustomerFeedback;

/*
 *@author: NSENGIYUMVA GERSHOM
 */

import com.customify.client.services.CustomerFeedbackService;
import com.customify.client.Keys;

import java.io.IOException;
import java.net.Socket;
import java.util.*;

public class CustomerReadFeedbacks {
    private Socket socket;

    public CustomerReadFeedbacks(Socket socket){
        this.socket = socket;
    }
    public Socket getSocket()
    {
        return socket;
    }
    public void setSocket(Socket socket){
        this.socket = socket;
    }

    /*
     * Here is the function to get all the recorded feedbacks
     */

    public void GetFeedbacks() throws IOException, ClassNotFoundException {
        String json = "{ \"key\" : \""+ Keys.GET_ALL_FEEDBACKS +"\" }";
        CustomerFeedbackService feedService = new CustomerFeedbackService(socket);
        feedService.getAllCustomerFeedbacks(json);
    }
    // -------------------------------------------------------------------------------

    /*
     * The function for deleting certain customer feedback
     */
//    public void deleteCustomerFeedback() throws IOException, ClassNotFoundException {
//        Scanner scan = new Scanner(System.in);
//        System.out.print("Enter customer id: \t");
//        int customerId = scan.nextInt();
//        String json = "{ \"customerId\" : \"" + customerId + "\", \"key\" : \"" + Keys.REMOVE_FEEDBACK + "\" }";
//        CustomerFeedbackService cFeedbackService = new CustomerFeedbackService(socket);
//        // cFeedbackService.deleteCustomerFeedback(json);
//        // scan.close();
//        // -------------------------------------------------------------------------------u
//    }
}
