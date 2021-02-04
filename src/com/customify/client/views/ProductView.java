/**
 * @description
 * A view made for manipulating products
 *
 * @author SAUVE Jean-Luc, Jacques Twizeyimana,Iradukunda Tamara Merlyn
 * @version 5
 * */
package com.customify.client.views;

import com.customify.client.services.ProductService;
import com.customify.server.models.ProductModel;
import com.customify.shared.requests_data_formats.ProductFormat;

import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class ProductView {
    private Socket socket;

    // Declare
    long productCode;
    int business_id;
    String name;
    float price;
    int quantity;
    String description;
    double bondedPoints;
    int registered_by;
    Date createdAt;

    public ProductView(Socket socket) {
        this.socket = socket;
    }

    //Ask for required data in order to Register a Product
    public void createProduct() throws Exception {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Enter product name:");
        name = scanner.nextLine();

        System.out.println("Enter business_id:");
        business_id = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter product price:");
        price = Float.parseFloat(scanner.nextLine());

        System.out.println("Enter quantity you have:");
        quantity = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter product description:");
        description = scanner.nextLine();

        System.out.println("Enter points to bind with:");
        bondedPoints = Double.parseDouble(scanner.nextLine());

        System.out.println("Who is registering this product?");
        registered_by = Integer.parseInt(scanner.nextLine());

        ProductFormat productFormat = new ProductFormat(productCode,business_id,name,price,quantity,description,bondedPoints,registered_by,createdAt);

        productFormat.setCreatedAt(new Date());
            ProductService productService = new ProductService(this.socket);
            productService.setSocket(this.socket);
            productService.addNewProduct(productFormat);



    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void getAll() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("\t\tHere is a list of products registered so far");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        }

}
