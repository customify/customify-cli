package com.customify.client.views.employee;


import com.customify.client.services.EmployeeService;
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
        System.out.println("\n\t\t\t---------------------------HOME | EMPLOYEE MANAGEMENT | ALL EMPLOYEES -------------------------------------------------------------------------------------\n");
        System.out.println("\t\t\t------------------------------------------------------------------------------------------------------------------------------------------------------------");

        System.out.println(String.format("\t\t\t%-30s %-30s %-30s %-30s %-30s", "EMPLOYEE ID","FIRST-NAME","LAST-NAME", "EMAIL","TITLE" ));
        System.out.println("\t\t\t------------------------------------------------------------------------------------------------------------------------------------------------------------");


        EmployeeService employeeService = new EmployeeService(this.socket);
        List<String> res = new ArrayList<>();
        res = employeeService.getAll();
        if (res != null)
        {
            for(int i = 0;i<res.size();i++)
            {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(res.get(i));
                System.out.println(String.format("\t\t\t%-30s %-30s %-30s %-30s %-30s", jsonNode.get("employeeId").asText(), jsonNode.get("firstName").asText(), jsonNode.get("lastName").asText(), jsonNode.get("email").asText(),jsonNode.get("title").asText()));


            }
        }
    }

}

