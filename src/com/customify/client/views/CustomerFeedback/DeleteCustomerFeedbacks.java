/*
 * @Author: Gershom Nsengiyumva
 * Description
 * 
*/

package com.customify.client.views.CustomerFeedback;

import com.customify.client.services.CustomerFeedbackService;
import com.customify.client.Keys;

import java.io.IOException;
import java.net.Socket;
import java.util.*;

public class DeleteCustomerFeedbacks {
    private Socket socket;

    public DeleteCustomerFeedbacks(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

//    public void deleteCustomerFeedback() throws IOException, ClassNotFoundException {
//        Scanner scan = new Scanner(System.in);
//        System.out.print("Enter customer id: \t");
//        int customerId = scan.nextInt();
//        String json = "{ \"custmerId\" : \"" + customerId + "\", \"key\" : \"" + Keys.REMOVE_FEEDBACK + "\" }";
//        CustomerFeedbackService cFeedbackService = new CustomerFeedbackService(socket);
//        cFeedbackService.getAllCustomerFeedbacks(json);
//        // scan.close();
//    }
}
