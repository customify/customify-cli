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

    public void deleteCustomerFeedback() throws IOException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter feedback id: \t");
        int businessId = scan.nextInt();
        String json = "{ \"businessId\" : \"" + businessId + "\", \"key\" : \"" + Keys.REMOVE_BUSINESS + "\" }";
        CustomerFeedbackService cFeedbackService = new CustomerFeedbackService(socket);
        cFeedbackService.getAllCustomerFeedbacks(json);
    }

}
