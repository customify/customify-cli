package com.customify.client.services;

import com.customify.client.Colors;
import com.customify.client.SendToServer;
import com.customify.client.data_format.Sale.SaleDataFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

public class SalesService {

    private final Socket socket;

    public SalesService(Socket socket) {
        this.socket = socket;
    }

    public void ViewAllSalesByCustomer(String saleId){
        System.out.println(Colors.ANSI_RED);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tNOTHING HERE YET");
        System.out.println(Colors.ANSI_RESET);
    }

    public void salesReports(String salesId){
        System.out.println(Colors.ANSI_RED);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tNOTHING HERE YET");
        System.out.println(Colors.ANSI_RESET);
    }

    public void getAllSales(String json) throws IOException {
        SendToServer sendToServer = new SendToServer(json,this.socket);
        if(sendToServer.send()){
            this.handleAllSalesResponse();
        }
    }

    public void create(SaleDataFormat saleDataFormat)  {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = objectMapper.writeValueAsString(saleDataFormat);
            SendToServer sendToServer = new SendToServer(jsonData,this.socket);


            if(sendToServer.send()){
                this.handleCreateSale();
            }

        }catch (IOException e){
          e.printStackTrace();
        }
    }

    private void handleAllSalesResponse() {
        try {
            InputStream inputStream = this.socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

             ObjectMapper objectMapper = new ObjectMapper();

            List res =(List) objectInputStream.readObject();



            Iterator itr = res.iterator();

            String leftAlignFormat = "| %-10s | %-10s | %-10s | %-11s | %-11s | %-10s |%n";


            System.out.println(Colors.ANSI_CYAN);
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tALL SALES");
            System.out.println(Colors.ANSI_RESET);
            System.out.format("\t\t\t\t\t\t\t\t\t\t\t\t\t+------------+------------+------------+-------------+-------------+------------+%n");
            System.out.format("\t\t\t\t\t\t\t\t\t\t\t\t\t| SaleId     | customerID |  quantity  |totalPrice   | employeeID  | productID  |%n");
            System.out.format("\t\t\t\t\t\t\t\t\t\t\t\t\t+------------+------------+------------+-------------+-------------+------------+%n");
            while (itr.hasNext()) {
                JsonNode jsonNode = objectMapper.readTree((String) itr.next());
                System.out.format("\t\t\t\t\t\t\t\t\t\t\t\t\t"+leftAlignFormat,jsonNode.get("saleId"),jsonNode.get("customerID").textValue(),jsonNode.get("quantity").textValue(),jsonNode.get("totalPrice").textValue(),jsonNode.get("employeeID").textValue(),jsonNode.get("productID").textValue());
            }
            System.out.format("\t\t\t\t\t\t\t\t\t\t\t\t\t+------------+------------+------------+-------------+-------------+------------+%n");
            System.out.println("\n");
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }
    }

   private void handleCreateSale()  {
        try{
            InputStream inputStream = this.socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            String res =(String) objectInputStream.readObject();

            System.out.println("\n");
            System.out.println(Colors.ANSI_BLUE+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+res+Colors.ANSI_RESET);
            System.out.println("\n");

        }catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

   }

}
