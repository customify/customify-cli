package com.customify.client.views;

import com.customify.client.services.ProductService;
import com.customify.shared.requests_data_formats.ProductFormat;

import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class ProductView {
    private Socket socket;

    public ProductView(Socket socket) {
        this.socket = socket;
    }

    public void createProduct() throws Exception {
        Scanner scanner = new Scanner(System.in);
        long productCode;
        int business_id;
        String name;
        float price;
        int quantity;
        String description;
        double bondedPoints;
        int registered_by;
        Date createdAt;

        productCode = 0;

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

        createdAt =new Date();

            ProductFormat productFormat = new ProductFormat(productCode, business_id,name,price,quantity,description,bondedPoints,registered_by,createdAt);
            ProductService productService = new ProductService(this.socket);
            productService.addNewProduct(productFormat);

            System.out.println("Product you registered has id of " + productFormat.getProductCode());

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
