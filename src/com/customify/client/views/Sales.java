package com.customify.client.views;


/*
* By Makuza Mugabo Verite
* On Feb 25/02/2021
* */

import com.customify.client.Keys;
import com.customify.client.data_format.Sale.NewSale;
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
                System.out.println("|---------------------------------------------------|");
                System.out.println("|              SALES MANAGEMENT                     |");
                System.out.println("|___________________________________________________|");
                System.out.println("| 1. Sell Product                                   |");
                System.out.println("| 2. List all sales                                 |");
                System.out.println("| 3. View sales Made by a customer                  |");
                System.out.println("| 4. Reports about sales                            |");
                System.out.println("| 00. Back                                          |");
                System.out.println("|---------------------------------------------------|");

                System.out.println("Enter your choice here: ");
                int choice = scanner.nextInt();

                switch (choice){
                    case 1:
                        this.SaleProductView();
                        break;
                    case 2:
                        this.ViewAllSales();
                        break;
                    case 4:
                        break;
                    case 00:
                        showView = false;
                        break;
                    default:
                        System.out.println("INVALID CHOICE!");
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

            System.out.println("id: "+userJson.get("id").asText());

            String productId;
            String customerId;
            String productID;
            String emp_id = userJson.get("id").asText();
            float pricePerEach;
            float quantity = 1;
            float amount;


            SalesService salesService = new SalesService(this.socket);

            System.out.println("|---------------------------------------------------|");
            System.out.println("|              SALES MANAGEMENT                     |");
            System.out.println("|___________________________________________________|");

            System.out.print("Enter Product id: ");
            productId = scanner.nextLine();
            System.out.print("Enter customer id: ");
            customerId = scanner.nextLine();

            System.out.print("Enter product id: ");
            productID = scanner.nextLine();

            System.out.print("Enter Price for each Product in numbers: ");
            pricePerEach = scanner.nextFloat();

            System.out.print("Enter the quantity: ");
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

        System.out.println("|---------------------------------------------------|");
        System.out.println("|              ALL SALES                            |");
        System.out.println("|___________________________________________________|");

        try{
            salesService.getAllSales(json);
        }catch (IOException ioException){
            System.out.println("Error happened along the way");
        }
    }
}
