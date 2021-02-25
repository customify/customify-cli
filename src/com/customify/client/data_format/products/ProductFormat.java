/**
 * @description
 * Class to provide the Product's json format structure to be sent as Requests
 * @author SAUVE Jean-Luc
 * @version 1
 * */

package com.customify.client.data_format.products;

import com.customify.client.Keys;

public class ProductFormat  {

    private Keys key;
    private int id;
    private long productCode;
    private int businessId;
    private String name;
    private float price;
    private int quantity;
    private String description;
    private double bondedPoints;
    private int registeredBy;
    private String createdAt;
    //Constructor
    public  ProductFormat(){
        this.productCode = this.generateCode();
    }
    public ProductFormat(int businessId, String name, float price, int quantity, String description, double bondedPoints, int registeredBy, String createdAt) {
        this.productCode = this.generateCode();
        this.businessId = businessId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.bondedPoints = bondedPoints;
        this.registeredBy = registeredBy;
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
    public int getBusinessId() {
        return businessId;
    }
    public void setBusinessId(int businessId) {
        this.businessId = businessId;
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
    public int getRegisteredBy() {
        return registeredBy;
    }
    public void setRegisteredBy(int registeredBy) {
        this.registeredBy = registeredBy;
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
    public Keys getKey() {
        return key;
    }
    public void setKey(Keys key) {
        this.key = key;
    }
}
