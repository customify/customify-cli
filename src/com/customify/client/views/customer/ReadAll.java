package com.customify.client.views.customer;

import com.customify.client.services.CustomerService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadAll {
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    private Socket socket;

    public ReadAll()
    { }

    public ReadAll(Socket socket)
    {
        this.socket = socket;
    }

    public void view() throws IOException {
        System.out.println("\n\t\t\t---------------------------HOME | CUSTOMER MANAGEMENT | ALL CUSTOMERS-------------------------------------------------\n");
        System.out.println("\t\t\t------------------------------------------------------------------------------------------------------------------------");

        System.out.println(String.format("\t\t\t%-25s %-25s %-25s %-25s %-25s", "CODE","FIRST-NAME","LAST-NAME", "EMAIL","STATUS" ));
        System.out.println("\t\t\t------------------------------------------------------------------------------------------------------------------------");


        CustomerService customerService = new CustomerService(this.socket);
        List<String> res = new ArrayList<>();
        res = customerService.getAll();
        if (res != null)
        {
           for(int i = 0;i<res.size();i++)
            {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(res.get(i));
                System.out.println(String.format("\t\t\t%-25s %-25s %-25s %-25s %-25s", jsonNode.get("code").asText(), jsonNode.get("firstName").asText(), jsonNode.get("lastName").asText(), jsonNode.get("email").asText(),jsonNode.get("stateDesc").asText()));


            }
        }
    }

}
