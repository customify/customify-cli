package com.customify.client.views;


import com.customify.client.data_format.SalesFormat;
import com.customify.client.services.ProductService;
import com.customify.client.services.SalesService;

import java.net.Socket;
import java.util.Scanner;

/* @yassin hagenimana
9 feb 2021. this is sales view deals with products bought.
that is add sales, get sales,delete sales etc.
*/

public class SalesView {
    Socket socket;
    public SalesView(Socket socket){ this.socket=socket; }

    public void SaleView(){

        Scanner reading= new Scanner(System.in);

        int choice;
        System.out.println("|------------------------------------|");
        System.out.println("| SALES VIEW                         |");
        System.out.println("|____________________________________|");
        System.out.println("|1. Add a sale                       |");
        System.out.println("|2. View All Sales                   |");
        System.out.println("|3. View Sale by Id                  |");
        System.out.println("|4. Delete Sales                     |");
        System.out.println("|5. Back                             |");
        System.out.println("|____________________________________|");
        System.out.println("\n\n");
        System.out.println("Enter Your choice: ");

        choice = reading.nextInt();

        switch(choice){
            case 1:
                this.AddSale();
                break;
            case 2:
                this.ViewAllSales();
                break;
            case 3:
                this.getSaleById();
                break;
            case 4:
                this.DeleteSales();
                break;
            default:
                System.out.println("Please You entered incorrect Choice");
        }
    }

    public void AddSale () {
        SalesFormat salesFormat=new SalesFormat();
        Scanner reader=new Scanner(System.in);

        System.out.println("|--------------------------------------------|");
        System.out.println("|     Add a sale.                            |");
        System.out.println("|--------------------------------------------|");

        System.out.println("Enter customer Id:");
        salesFormat.setCustomer_id(reader.nextLine());
        System.out.println("Enter product Id:");
        salesFormat.setProduct_id(reader.nextLine());
        System.out.println("Enter Quantity:");
        salesFormat.setQuantity(reader.nextLine());
        System.out.println("Enter Total Price:");
        salesFormat.setTotalPrice(reader.nextLine());

        SalesService salesService=new  SalesService(this.socket);
        salesService.create(salesFormat);
        this.SaleView();

    }
    public  void ViewAllSales() {
        System.out.println("|--------------------------------------------|");
        System.out.println("|     All made sales                         |");
        System.out.println("|--------------------------------------------|");
        System.out.println("List of all made sales.");

        SalesService salesService=new  SalesService(this.socket);
        salesService.getAllSales();
        this.SaleView();
    }
    public void  DeleteSales() {
        Scanner reading = new Scanner(System.in);
        String Sales_id;
        System.out.println("Enter product Code:");
        Sales_id=reading.next();
        SalesService salesService= new SalesService(this.socket);
        salesService.deleteSale(Sales_id);

        this.SaleView();
    }
    public void getSaleById(){
        Scanner reading = new Scanner(System.in);
        String saleId;
        System.out.println("Enter Sale Id:");
        saleId =reading.nextLine();
        SalesService salesService= new SalesService(this.socket);
        salesService.getSaleById(saleId);

        this.SaleView();
    }


}
