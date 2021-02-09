package com.customify.client.views;

import com.customify.client.data_format.SalesFormat;

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
        System.out.println("|2. View Sales                       |");
        System.out.println("|3. Delete Sales                     |");
        System.out.println("|4. Back                             |");
        System.out.println("|____________________________________|");
        System.out.println("\n\n");
        System.out.println("Enter Your choice: ");

        choice = reading.nextInt();

        switch(choice){
            case 1:
                this.AddSale();
                break;
            case 2:
                this.ViewSales();
                break;
            case 3:
                this.DeleteSales();
                break;
            default:
                System.out.println("Please You entered incorrect Choice");
        }
    }

    public void AddSale(){
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

    }
    public  void ViewSales(){}
    public void  DeleteSales(){}


}
