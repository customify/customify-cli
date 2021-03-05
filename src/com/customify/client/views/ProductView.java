package com.customify.client.views;

import com.customify.client.Colors;
import com.customify.client.Keys;
import com.customify.client.services.ProductService;
import com.customify.client.data_format.products.*;
import com.customify.client.utils.authorization.UserSession;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.Socket;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

import static com.customify.client.Keys.UPDATE_PRODUCT;

public class ProductView {
    private Socket socket;

    public ProductView(Socket socket) {
        this.socket = socket;
    }

    public void init() throws Exception {
        Scanner reader = new Scanner(System.in);
        int choice;

        System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + Colors.ANSI_CYAN + "HOME >> PRODUCT MANAGEMENT"+ Colors.ANSI_RESET+"\n");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t1.register product");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t2.get all products");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t3.get product by id");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t4.update product");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t5.Delete product");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t00.back");

        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t" + Colors.ANSI_GREEN +"Enter option:\t"+Colors.ANSI_RESET);
        choice = Integer.parseInt(reader.nextLine());

        switch (choice) {
            case 0:
                return;
            case 1:
                this.createProduct();
                break;
            case 3:
                this.getProductById();
                break;
            case 4:
                this.updateProduct();
            case 2:
                this.getAll();
                break;
            case 5:
                this.deleteProduct();
                break;
            default:
                System.out.println("Your entered Incorrect option");
        }
    }

    public void createProduct() throws Exception {
        JsonNode session = new ObjectMapper().readTree(new UserSession().getUserJsonObject());

        Scanner scanner = new Scanner(System.in);
        ProductFormat newProduct = new ProductFormat();

        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\tEnter product name: ");
        newProduct.setName(scanner.nextLine());

//        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\tEnter business id: ");
//        newProduct.setBusiness_id(Integer.parseInt(scanner.nextLine()));
        newProduct.setBusiness_id(session.get("business_id").asInt());

        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\tEnter product price: ");
        newProduct.setPrice(Float.parseFloat(scanner.nextLine()));

        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\tEnter quantity you have: ");
        newProduct.setQuantity(Integer.parseInt(scanner.nextLine()));

        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\tEnter product description: ");
        newProduct.setDescription(scanner.nextLine());

        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\tEnter points to bind with: ");
        newProduct.setBondedPoints(Double.parseDouble(scanner.nextLine()));

//        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\tWho is registering this product: ");
//        newProduct.setRegistered_by(Integer.parseInt(scanner.nextLine()));
        newProduct.setRegistered_by(session.get("id").asInt());

        newProduct.setCreatedAt(LocalDate.now().toString());

        ProductService productService = new ProductService(this.socket);
        productService.addNewProduct(newProduct);
    }

    /**
     * @description
     * Method to provide Required Id to Product Service for Retrieving product by id
     * @author SAUVE Jean-Luc
     * @version 1
     * */
    public Integer getProductById() throws Exception {
        Scanner scanner = new Scanner(System.in);
        Integer productId;
        System.out.println("Enter Product Id:");
        productId =scanner.nextInt();
        ProductService productService = new ProductService(this.socket);
        productService.getProductById(productId);
        return productId;
    }

    /**
     * @description
     * Method to provide Required Id to Product Service for Updating a product
     * @author SAUVE Jean-Luc
     * @version 1
     * */
    public void updateProduct() throws Exception {
//        productId = getProductById();
        Scanner scanner = new Scanner(System.in);

//        System.out.println("You are going to update the above product");

        ProductFormat newProduct = new ProductFormat();
        ProductService productService = new ProductService(this.socket);

        newProduct.setKey(UPDATE_PRODUCT);

        System.out.println("Enter Product Id: ");
        newProduct.setId(Integer.parseInt(scanner.nextLine()));

        productService.getProductById(newProduct.getId());

        System.out.println("Enter NEW product code");
        newProduct.setProductCode(Long.parseLong(scanner.nextLine()));

        System.out.println("Enter NEW business_id:");
        newProduct.setBusiness_id(Integer.parseInt(scanner.nextLine()));

        System.out.println("Enter NEW product name:");
        newProduct.setName(scanner.nextLine());

        System.out.println("Enter NEW product price:");
        newProduct.setPrice(Float.parseFloat(scanner.nextLine()));

        System.out.println("Enter NEW quantity you have:");
        newProduct.setQuantity(Integer.parseInt(scanner.nextLine()));

        System.out.println("Enter NEW product description:");
        newProduct.setDescription(scanner.nextLine());

        System.out.println("Enter NEW points to bind with:");
        newProduct.setBondedPoints(Double.parseDouble(scanner.nextLine()));

        System.out.println("Who is NEWLY registering this product?");
        newProduct.setRegistered_by(Integer.parseInt(scanner.nextLine()));

//        LocalDate myObj = LocalDate.now();

        newProduct.setCreatedAt(LocalDate.now().toString());


        productService.updateProduct(newProduct);

    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void getAll() throws Exception {
        ProductService productService = new ProductService(this.socket);
        productService.getAllProducts();

    }


    /**
     * @description
     * Method to delete a product from using it's code
     * @author Tamara Iradukunda
     * @version 1
     * */
    public void deleteProduct() throws Exception{
        Scanner scanner = new Scanner(System.in);
        ProductFormat oldProduct = new ProductFormat();
        oldProduct.setKey(Keys.DELETE_PRODUCT);
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\tEnter product Code: ");
        oldProduct.setProductCode(Long.parseLong(scanner.nextLine()));
        ProductService productService = new ProductService(this.socket);
        productService.deleteProduct(oldProduct);}
}
