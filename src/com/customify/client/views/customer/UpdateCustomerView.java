package com.customify.client.views.customer;

/**
 * @author Nshimiye Emmy
 * @role this is the class to implement the update customer view to show interface of update customer
 */

import com.customify.client.Keys;
import com.customify.client.SendToServer;
import com.customify.client.data_format.GetCustomer;
import com.customify.client.services.CustomerService;
import com.customify.client.data_format.UpdateCustomerFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class UpdateCustomerView {
    private Socket socket;

    public UpdateCustomerView() {
    }

    public UpdateCustomerView(Socket socket) {
        this.socket = socket;
    }

    public void view() throws IOException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        CustomerService service = new CustomerService(this.socket);
        System.out.println("\t\t\t------------------HOME >> CUSTOMER MANAGEMENT >> UPDATE-CUSTOMER---------------------");
        System.out.println("\n\t\t\t\t\t\t  00. Return ");
        System.out.println("\n\t\t\t\t\t\t  Enter  Customer Code:");
        String customer_code = scan.nextLine();

            List<String> response = service.get(new GetCustomer(Keys.GET_CUSTOMER, customer_code));
            if (response != null) {
                JsonNode jsonNode = new ObjectMapper().readTree(response.get(0));
                System.out.println("\t\t\t............Customer " + jsonNode.get("code")+ " Details " + "..........");
                System.out.println("\t\t\t\t\t\tFirst Name " + jsonNode.get("firstName"));
                System.out.println("\t\t\t\t\t\tLast Name " + jsonNode.get("lastName"));
                System.out.println("\t\t\t\t\t\tEmail " + jsonNode.get("email"));
                //System.out.println("Code " + jsonNode.get("code"));

                UpdateCustomerFormat format = new UpdateCustomerFormat();
                format.setKey(Keys.UPDATE_CUSTOMER);

                System.out.println("\n\t\t\t\tEnter your first name to update:");
                format.setFirstName(scan.nextLine());

                System.out.println("\t\t\t\tEnter your last name to update:");
                format.setLastName(scan.nextLine());

                System.out.println("\t\t\t\tEnter your email address to update:");
                format.setEmail(scan.nextLine());
                format.setCustomerCode(jsonNode.get("code").asText());

                SendToServer sendToServer = new SendToServer(new ObjectMapper().writeValueAsString(format), this.socket);

                if (sendToServer.send()) {
                    InputStream input = this.socket.getInputStream();
                    ObjectInputStream inputStream = new ObjectInputStream(input);
                    ObjectMapper mapper = new ObjectMapper();

                    List<String> data = (List<String>) inputStream.readObject();
                    System.out.println(data.get(0));

                } else System.out.println("\t\tFAILED TO SEND TO SERVER");

            }

        }
    }



