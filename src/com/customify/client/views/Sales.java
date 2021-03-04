/*
 * By Makuza Mugabo Verite
 * On Feb 25/02/2021
 * */


package com.customify.client.views;



import com.customify.client.Colors;
import com.customify.client.Keys;
import com.customify.client.data_format.Sale.SaleDataFormat;
import com.customify.client.services.SalesService;
import com.customify.client.utils.authorization.UserSession;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Sales {
    private Socket socket;
    private boolean isLoggedIn = false;

    public Sales(Socket socket, boolean isLoggedIn) throws JsonProcessingException {
       setLoggedIn(isLoggedIn);
       setSocket(socket);

       if(this.isLoggedIn())
           this.main();
    }

    public void main() throws JsonProcessingException {

        Scanner scanner = new Scanner(System.in);
        boolean showView = true;

      do {
              this.Header();
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t1. Sell Product");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t2. List all sales");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t3. View sales Made by a customer");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t4. Reports about sales");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t00. Back \n");
                System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\tEnter your choice"+Colors.ANSI_YELLOW+" <1-00>"+Colors.ANSI_RESET+": ");

          int choice = scanner.nextInt();
                switch (choice){
                    case 1:
                        this.SaleProductView();
                        break;
                    case 2:
                        this.ViewAllSales();
                        break;
                    case 3:
                        this.ViewAllSalesByCustomer();
                        break;
                    case 4:
                        this.ViewAllSalesReports();
                        break;
                    case 00:
                        showView = false;
                        break;
                    default:
                        System.out.println(Colors.ANSI_RED+"\t\t\t\t\t\t\t\t\t\t\t\t\t\tINVALID CHOICE"+Colors.ANSI_RESET);
                }
            }while (showView);
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public void SaleProductView()  {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            Scanner scanner = new Scanner(System.in);
            UserSession userSession = new UserSession();
            JsonNode userJson = objectMapper.readTree(userSession.getUserJsonObject());

            String productId;
            String customerId;
            String productID;
            String emp_id = userJson.get("id").asText();
            float pricePerEach;
            float quantity = 1;
            float amount;


            SalesService salesService = new SalesService(this.socket);

            this.Header();

            System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\tEnter customer id: ");
            customerId = scanner.nextLine();

            System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\tEnter product id: ");
            productID = scanner.nextLine();

            System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\tEnter Price for each Product in numbers: ");
            pricePerEach = scanner.nextFloat();

            System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\tEnter the quantity: ");
            quantity = scanner.nextFloat();

            amount  = pricePerEach * quantity;


            String amountString = amount + " RWF";

            SaleDataFormat newSale = new SaleDataFormat(customerId,Float.toString(quantity),amountString,emp_id,productID);
            salesService.create(newSale);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }

    public void ViewAllSales() {

        String json = "{ \"key\" : \""+ Keys.GET_ALL_SALES +"\" }";
        SalesService salesService = new SalesService(this.socket);

       this.Header();

        try{
            salesService.getAllSales(json);
        }catch (IOException ioException){
            System.out.println("Error happened along the way");
        }
    }

    public void ViewAllSalesByCustomer() {

        String json = "{ \"key\" : \""+ Keys.GET_ALL_SALES +"\" }";
        SalesService salesService = new SalesService(this.socket);

        this.Header();

        salesService.ViewAllSalesByCustomer(json);
    }

    public void ViewAllSalesReports() {

        String json = "{ \"key\" : \""+ Keys.GET_ALL_SALES +"\" }";
        SalesService salesService = new SalesService(this.socket);

        this.Header();

        salesService.salesReports(json);
    }

    public void Header(){
        System.out.println(Colors.ANSI_GREEN);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tCUSTOMIFY SALES MANAGEMENT");
        System.out.println(Colors.ANSI_RESET);
    }
}

