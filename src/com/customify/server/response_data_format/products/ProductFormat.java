/**
 * @description
 * Class to provide the format of Responses sent to client regarding Products.
 * @author SAUVE Jean-Luc
 * @version 1
 * */

package com.customify.server.response_data_format.products;


public class ProductFormat{
    private int status;
    private int id;
    private long productCode;
    private int business_id;
    private String name;
    private float price;
    private int quantity;
    private String description;
    private double bondedPoints;
    private int registered_by;
    private String createdAt;
    //Constructor
    public  ProductFormat(){
        this.productCode = this.generateCode();
    }
    public ProductFormat(int business_id, String name, float price, int quantity, String description, double bondedPoints, int registered_by, String createdAt) {
        this.productCode = this.generateCode();
        this.business_id = business_id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.bondedPoints = bondedPoints;
        this.registered_by = registered_by;
        this.createdAt = createdAt;
    }
    public long generateCode(){
        int max = 99999999;
        int min=100000;

        return (long) (Math.random() * (max - min + 1) + min);
    }
    //Getters and Setters
    public long getProductCode() {
        return productCode;
    }
    public void setProductCode(long productCode) {
        this.productCode = productCode;
    }
    public int getBusiness_id() {
        return business_id;
    }
    public void setBusiness_id(int business_id) {
        this.business_id = business_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getBondedPoints() {
        return bondedPoints;
    }
    public void setBondedPoints(double bondedPoints) {
        this.bondedPoints = bondedPoints;
    }
    public int getRegistered_by() {
        return registered_by;
    }
    public void setRegistered_by(int registered_by) {
        this.registered_by = registered_by;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}