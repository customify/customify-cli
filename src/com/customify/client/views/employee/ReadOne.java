package com.customify.client.views.employee;
import com.customify.client.Keys;
import com.customify.client.data_format.GetEmployee;
import com.customify.client.services.EmployeeService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadOne {
    private Socket socket;

    public ReadOne(){ }

    public ReadOne(Socket socket) {
        this.socket = socket;
    }

    public void view() throws IOException, ClassNotFoundException {
        boolean customerView = true;
        Scanner scan = new Scanner(System.in);
        search:do{
            System.out.println("\n\t\t\t---------------------------HOME | EMPLOYEE MANAGEMENT | SEARCH EMPLOYEE------------------------------------------------------------------------------------\n");
            System.out.println("\n\t\t\t| 0. Return ");
            System.out.println("\n\t\t\tEnter  Employee ID :");
            int employeeId = 0;
            try {
                employeeId = scan.nextInt();
            } catch (Exception e) {
                System.out.println("\n\t\t\t*******************************************************************************************************");
                System.out.println("                                             NO EMPLOYEE FOUND (INVALID ID)                                    ");
                System.out.println("\t\t\t*******************************************************************************************************");
            }
            if (employeeId == 0)
                break search;

            List<String> res = null;
            try {
                GetEmployee format = new GetEmployee(Keys.GET_EMPLOYEE, employeeId);
                EmployeeService employeeService = new EmployeeService(this.socket);
                res = new ArrayList<>();
                res = employeeService.get(format);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (res != null )
            {
                System.out.println("\n\t\t\t------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(String.format("\t\t\t%-30s %-30s %-30s %-30s %-30s", "EMPLOYEE ID","FIRST-NAME","LAST-NAME", "EMAIL","TITLE" ));
                System.out.println("\t\t\t------------------------------------------------------------------------------------------------------------------------------------------------------------");
                for(int i = 0;i<res.size();i++)
                {

                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(res.get(i));
                        System.out.println(String.format("\t\t\t%-30s %-30s %-30s %-30s %-30s", jsonNode.get("employeeId").asText(), jsonNode.get("firstName").asText(), jsonNode.get("lastName").asText(), jsonNode.get("email").asText(),jsonNode.get("title").asText()));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }


                }
            }
        }while(true);
    }


}
